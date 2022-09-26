package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 留言板 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    /**
     * 访客评论
     * @param articleComment
     * @return
     */
    int saveUserMessage(ArticleComment articleComment);

    List<ArticleComment> selectCommentList(ArticleComment articleComment);

    int reply(CommentInfoVo commentInfoVo);

    CommentInfoVo selectCommentById(String id);

    int updateComment(CommentInfoVo commentInfoVo);

    void deleteCommentById(String[] ids);

    /**
     * 前台显示留言板列表
     * @return
     */
    Map<String,Object> getMessageList(Integer pageNumber);

    /**
     * 前台留言板数据校验
     * @param articleComment
     * @return
     */
    boolean checkFrontData(ArticleComment articleComment);

    /**
     * 前台显示文章评论列表
     * @param articleId
     * @return
     */
    Map<String, Object> getListArticle(String articleId);

    boolean auditCommentById(String[] ids, String type);
}
