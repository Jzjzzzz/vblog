package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.ArticleComment;
import com.jzj.vblog.web.pojo.vo.CommentInfoVo;

import java.util.List;

/**
 * <p>
 * 留言板 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11 11:12
 */
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    List<ArticleComment> selectCommentList(ArticleComment articleComment);

    CommentInfoVo selectCommentById(String id);
}
