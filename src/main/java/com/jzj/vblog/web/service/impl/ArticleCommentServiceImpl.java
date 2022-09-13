package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.mapper.ArticleCommentMapper;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;
import com.jzj.vblog.web.service.ArticleCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

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

    @Autowired
    private ArticleCommentMapper articleCommentMapper;


    /**
     * 访客评论留言板
     * @param articleComment
     * @return
     */
    @Override
    public int saveUserMessage(ArticleComment articleComment) {
        //评论类型(0-留言板,1-文章评论)
        articleComment.setCommentType("0");
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
        sonModel.setCommentType("0");
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

    @Override
    public int deleteCommentByIds(List<String> ids) {
        return articleCommentMapper.deleteBatchIds(ids);
    }
}
