package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.sign.EmailUtil;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.ArticleCommentMapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.vo.CommentFrontListVo;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11
 */
@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    private final static Integer SIZE = 1;

    @Autowired
    private ArticleCommentMapper articleCommentMapper;


    /**
     * 访客评论
     * @param articleComment
     * @return
     */
    @Override
    public int saveUserMessage(ArticleComment articleComment) {
        articleComment.setStatus("0");
        //是否为父节点
        articleComment.setParentStatus("1");
        return articleCommentMapper.insert(articleComment);
    }

    @Override
    public List<ArticleComment> selectCommentList(ArticleComment articleComment) {
        return articleCommentMapper.selectCommentList(articleComment);
    }

    @Transactional
    @Override
    public int reply(CommentInfoVo commentInfoVo) {
        //修改父节点状态
        ArticleComment parentModel = articleCommentMapper.selectById(commentInfoVo.getId());
        parentModel.setStatus("1");
        articleCommentMapper.updateById(parentModel);
        //新增子节点
        ArticleComment sonModel = new ArticleComment();
        sonModel.setNickName("漫漫长路");
        sonModel.setEmail("946232976@qq.com");
        sonModel.setStatus("1");
        sonModel.setParentId(parentModel.getId());
        sonModel.setContent(commentInfoVo.getReply());
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
    public int deleteCommentById(String id) {
        ArticleComment sonModel = articleCommentMapper.selectOne(new QueryWrapper<ArticleComment>().eq("parent_id", id));
        if(sonModel!=null){
            articleCommentMapper.deleteById(sonModel.getId());
        }
        return articleCommentMapper.deleteById(id);
    }

    /**
     * 前台显示文章评论列表
     * @param articleId
     * @return
     */
    @Override
    public Map<String, Object> getListArticle(String articleId) {
        List<ArticleComment> commentList = articleCommentMapper.selectList(
                new QueryWrapper<ArticleComment>()
                        .eq("parent_status", "1")
                        .eq("comment_type", "1")
                        .eq("article_id",articleId)
                        .orderByDesc("create_time"));
        HashMap<String, Object> map = new HashMap<>();
        List<CommentFrontListVo> voList = getCommentFrontListVos(commentList);
        map.put("list",voList);
        return map;
    }

    /**
     * 前台显示留言板列表
     * @return
     */
    @Override
    public Map<String,Object> getMessageList(Integer pageNumber) {
        IPage<ArticleComment> commentPage = articleCommentMapper.selectPage(
                new Page<>(pageNumber, 1),
                new QueryWrapper<ArticleComment>()
                        .eq("parent_status", "1")
                        .eq("comment_type","0")
                        .orderByDesc("create_time"));
        HashMap<String, Object> map = new HashMap<>();
        List<ArticleComment> commentList = commentPage.getRecords();
        List<CommentFrontListVo> voList = getCommentFrontListVos(commentList);
        map.put("list",voList);
        long total = commentPage.getTotal();
        map.put("total",total);
        map.put("isFinish",total<=SIZE*pageNumber);
        return map;
    }

    private List<CommentFrontListVo> getCommentFrontListVos(List<ArticleComment> commentList) {
        List<CommentFrontListVo> voList = new ArrayList<>();
        for (ArticleComment comment : commentList) {
            CommentFrontListVo vo = new CommentFrontListVo();
            ArticleComment sonModel = articleCommentMapper.selectOne(new QueryWrapper<ArticleComment>().eq("parent_id", comment.getId())); //子节点
            if(sonModel!=null){
                vo.setId(sonModel.getId());
                vo.setNickname(sonModel.getNickName());
                vo.setDate(sonModel.getCreateTime());
                vo.setMessage(sonModel.getContent());
                vo.setUserPhoto("https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/11/894545a484d24866bcf4f928914dda89.jpg");
                vo.setArea("广东东莞");
                vo.setResponse(comment.getContent());
                vo.setResponseName(comment.getNickName());
                vo.setResponseTime(comment.getCreateTime());
            }else {
                vo.setDate(comment.getCreateTime());
                vo.setNickname(comment.getNickName());
                vo.setMessage(comment.getContent());
                vo.setUserPhoto("https://vue-vblog.oss-cn-shenzhen.aliyuncs.com/webLogo/2022/08/11/894545a484d24866bcf4f928914dda89.jpg");
                vo.setArea("广东东莞");
            }
            voList.add(vo);
        }
        return voList;
    }



    /**
     * 前台留言板数据校验
     * @param articleComment
     * @return
     */
    @Override
    public boolean checkFrontData(ArticleComment articleComment) {
        String nickName = articleComment.getNickName();
        String content = articleComment.getContent();
        String email = articleComment.getEmail();
        //非空校验
        if(StringUtils.isEmpty(nickName) || StringUtils.isEmpty(content) || StringUtils.isEmpty(email)){
            return false;
        }
        //长度校验
        if(nickName.length()>20 || content.length()>200 || email.length()>30){
            return false;
        }
        if(!EmailUtil.isEmail(email)){
            return false;
        }
        return true;
    }


}
