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
 * @since 2022-09-11 11:12
 */
public interface ArticleCommentService extends IService<ArticleComment> {

    /**
     * 保存访客评论
     *
     * @param articleComment 文章评论实体
     * @return 变动条数
     */
    int saveUserMessage(ArticleComment articleComment);

    /**
     * 分页列表查询
     *
     * @param articleComment 查询条件
     * @return 列表
     */
    List<ArticleComment> selectCommentList(ArticleComment articleComment);

    /**
     * 回复评论
     *
     * @param commentInfoVo 评论详情vo
     * @return 变动条数
     */
    int reply(CommentInfoVo commentInfoVo);

    /**
     * 根据id查询评论详情
     *
     * @param id 评论id
     * @return 评论详情vo
     */
    CommentInfoVo selectCommentById(String id);

    /**
     * 更新评论
     *
     * @param commentInfoVo 评论详情vo
     * @return 变动条数
     */
    int updateComment(CommentInfoVo commentInfoVo);

    /**
     * 批量删除批量
     *
     * @param ids 评论id集合
     * @return 是否成功
     */
    boolean deleteCommentById(String[] ids);

    /**
     * 前台显示留言板列表
     *
     * @param pageNumber 页码
     * @return 列表
     */
    Map<String, Object> getMessageList(Integer pageNumber);

    /**
     * 前台留言板数据校验
     *
     * @param articleComment 文章评论实体
     * @return 是否成功
     */
    boolean checkFrontData(ArticleComment articleComment);

    /**
     * 前台显示文章评论列表
     *
     * @param articleId 文章id
     * @return 列表
     */
    Map<String, Object> getListArticle(String articleId);

    /**
     * 根据id审核评论
     *
     * @param ids 评论id集合
     * @return 是否成功
     */
    boolean auditCommentById(String[] ids, String type);
}
