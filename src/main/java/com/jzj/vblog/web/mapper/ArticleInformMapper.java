package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;
import com.jzj.vblog.web.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章基础信息 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
public interface ArticleInformMapper extends BaseMapper<ArticleInform> {

    /**
     * 后台查询文章
     * @param entity
     * @return
     */
    List<ArticleInform> selectArticleList(ArticleInform entity);

    /**
     * 前台分页查询文章列表
     * @param page
     * @return
     */
    Page<ArticleVo> selectPageVo(@Param("page") Page<ArticleVo> page);

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    ArticleAddVo selectArticleByIdVo(@Param("id") String id);
}
