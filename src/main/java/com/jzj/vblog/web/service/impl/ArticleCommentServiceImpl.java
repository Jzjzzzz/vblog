package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.aspectj.manager.AsyncFactory;
import com.jzj.vblog.aspectj.manager.AsyncManager;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.constant.RabbitConstants;
import com.jzj.vblog.utils.sign.*;
import com.jzj.vblog.web.mapper.ArticleCommentMapper;
import com.jzj.vblog.web.mapper.ArticleInformMapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;
import com.jzj.vblog.web.pojo.entity.rabbit.EmailMessageStruct;
import com.jzj.vblog.web.pojo.vo.CommentFrontListVo;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.SysWebInformationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11 11:12
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    private final static Integer SIZE = 1;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Autowired
    private SysWebInformationService webInformationService;

    @Autowired
    private SysConfigService configService;

    @Autowired
    private ArticleInformMapper articleInformMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public int saveUserMessage(ArticleComment articleComment) {
        try {
            //获取站点信息
            SysWebInformation information = webInformationService.selectWebInformationById();
            articleComment.setStatus("0");
            articleComment.setAvatar(information.getCommentImg());
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            articleComment.setIp(ip);
            if (ip == null || IpUtils.internalIp(ip)) {
                articleComment.setCity("未知地址");
            } else {
                String address = AddressUtils.getRealAddressByIP(ip);
                // String[] citys = Objects.requireNonNull(IpUtils.getCityInfo(ip)).split("\\|");
                // articleComment.setCity(citys[2]+citys[3]);
                articleComment.setCity(address);
            }
            //是否为父节点
            articleComment.setParentStatus("1");
            articleComment.setAuditStatus("0");
            return articleCommentMapper.insert(articleComment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ArticleComment> selectCommentList(ArticleComment articleComment) {
        return articleCommentMapper.selectCommentList(articleComment);
    }

    @Transactional
    @Override
    public int reply(CommentInfoVo commentInfoVo) {
        //获取站点信息
        SysWebInformation information = webInformationService.selectWebInformationById();
        //修改父节点状态
        ArticleComment parentModel = articleCommentMapper.selectById(commentInfoVo.getId());
        parentModel.setStatus("1");
        articleCommentMapper.updateById(parentModel);
        //新增子节点
        ArticleComment sonModel = new ArticleComment();
        sonModel.setNickName(information.getWebName() == null ? "漫漫长路" : information.getWebName());
        sonModel.setEmail(information.getEmail() == null ? "946232976@qq.com" : information.getEmail());
        sonModel.setStatus("1");
        sonModel.setParentId(parentModel.getId());
        sonModel.setContent(commentInfoVo.getReply());
        //发送邮件通知留言者
        if ("true".equals(configService.selectConfigByKey(CacheConstants.SYS_EMAIL_ENABLE))) {
            if ("true".equals(configService.selectConfigByKey(CacheConstants.SYS_RABBIT_ENABLE))) {
                rabbitTemplate.convertAndSend(RabbitConstants.EMAIL_QUEUE, new EmailMessageStruct(parentModel.getEmail(), sonModel.getNickName(), commentInfoVo.getReply()));
            } else {
                AsyncManager.me().execute(AsyncFactory.sendMail(
                        parentModel.getEmail(),
                        sonModel.getNickName() + "对您留言的回复-来着漫漫长路的博客",
                        commentInfoVo.getReply()));
            }
        }
        return articleCommentMapper.insert(sonModel);
    }

    @Override
    public CommentInfoVo selectCommentById(String id) {
        return articleCommentMapper.selectCommentById(id);
    }

    @Override
    public int updateComment(CommentInfoVo commentInfoVo) {
        ArticleComment sonModel = articleCommentMapper.selectById(commentInfoVo.getSid());
        sonModel.setContent(commentInfoVo.getReply());
        return articleCommentMapper.updateById(sonModel);
    }

    @Transactional
    @Override
    public boolean deleteCommentById(String[] ids) {
        try {
            for (String id : ids) {
                ArticleComment comment = articleCommentMapper.selectById(id);
                if ("1".equals(comment.getCommentType()) && "1".equals(comment.getAuditStatus())) {
                    ArticleInform inform = articleInformMapper.selectById(comment.getArticleId());
                    inform.setCommentNumber(inform.getCommentNumber() - 1);
                    articleInformMapper.updateById(inform);
                }
                ArticleComment sonModel = articleCommentMapper.selectOne(new QueryWrapper<ArticleComment>().eq("parent_id", id));
                if (sonModel != null) {
                    articleCommentMapper.deleteById(sonModel.getId());
                }
                articleCommentMapper.deleteById(id);
            }
            return true;
        } catch (Exception e) {
            log.error("批量删除评论出错", e);
            return false;
        }
    }

    @Override
    public Map<String, Object> getListArticle(String articleId) {
        List<ArticleComment> commentList = articleCommentMapper.selectList(
                new QueryWrapper<ArticleComment>()
                        .eq("parent_status", "1")
                        .eq("comment_type", "1")
                        .eq("article_id", articleId)
                        .eq("audit_status", "1")
                        .orderByDesc("create_time"));
        HashMap<String, Object> map = new HashMap<>();
        List<CommentFrontListVo> voList = getCommentFrontListVos(commentList);
        map.put("list", voList);
        return map;
    }

    @Transactional
    @Override
    public boolean auditCommentById(String[] ids, String type) {
        List<ArticleComment> list = articleCommentMapper.selectBatchIds(Arrays.asList(ids));
        for (ArticleComment comment : list) {
            comment.setAuditStatus(type);
            if ("1".equals(comment.getCommentType())) {
                ArticleInform inform = articleInformMapper.selectById(comment.getArticleId());
                inform.setCommentNumber(inform.getCommentNumber() + 1);
                articleInformMapper.updateById(inform);
            }
        }
        return this.updateBatchById(list);
    }

    @Override
    public Map<String, Object> getMessageList(Integer pageNumber) {
        IPage<ArticleComment> commentPage = articleCommentMapper.selectPage(
                new Page<>(pageNumber, 5),
                new QueryWrapper<ArticleComment>()
                        .eq("parent_status", "1")
                        .eq("comment_type", "0")
                        .eq("audit_status", "1")
                        .orderByDesc("create_time"));
        HashMap<String, Object> map = new HashMap<>();
        List<ArticleComment> commentList = commentPage.getRecords();
        List<CommentFrontListVo> voList = getCommentFrontListVos(commentList);
        map.put("list", voList);
        long total = commentPage.getTotal();
        map.put("total", total);
        map.put("isFinish", total <= (long) SIZE * pageNumber);
        return map;
    }

    private List<CommentFrontListVo> getCommentFrontListVos(List<ArticleComment> commentList) {
        //获取站点信息
        SysWebInformation information = webInformationService.selectWebInformationById();
        List<CommentFrontListVo> voList = new ArrayList<>();
        for (ArticleComment comment : commentList) {
            CommentFrontListVo vo = new CommentFrontListVo();
            ArticleComment sonModel = articleCommentMapper.selectOne(new QueryWrapper<ArticleComment>().eq("parent_id", comment.getId())); //子节点
            if (sonModel != null) {
                vo.setId(sonModel.getId());
                vo.setNickname(sonModel.getNickName());
                vo.setDate(sonModel.getCreateTime());
                vo.setMessage(sonModel.getContent());
                vo.setUserPhoto(information.getWebAvatar());
                vo.setArea("广东广州");
                vo.setResponse(comment.getContent());
                vo.setResponseName(comment.getNickName());
                vo.setResponseTime(comment.getCreateTime());
            } else {
                vo.setDate(comment.getCreateTime());
                vo.setNickname(comment.getNickName());
                vo.setMessage(comment.getContent());
                vo.setUserPhoto(comment.getAvatar());
                vo.setArea(comment.getCity());
            }
            voList.add(vo);
        }
        return voList;
    }


    @Override
    public boolean checkFrontData(ArticleComment articleComment) {
        try {
            String nickName = articleComment.getNickName();
            String content = articleComment.getContent();
            String email = articleComment.getEmail();
            //非空校验
            if (StringUtils.isEmpty(nickName) || StringUtils.isEmpty(content) || StringUtils.isEmpty(email)) {
                return false;
            }
            //长度校验
            if (nickName.length() > 20 || content.length() > 200 || email.length() > 30) {
                return false;
            }
            //邮箱类型
            if (!EmailUtil.isEmail(email)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
