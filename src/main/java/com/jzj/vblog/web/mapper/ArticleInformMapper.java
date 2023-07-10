package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.vo.*;
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
    Page<ArticleVo> selectPageVo(@Param("page") Page<ArticleVo> page, @Param("tagId") String tagId,@Param("summaryId") String summaryId);

    /**
     * 根据id获取文章
     *
     * @param id
     * @return
     */
    ArticleAddVo selectArticleByIdVo(@Param("id") String id);

    ArticleFrontVo selectFrontArticleByIdVo(String id);

    /**
     * 获取文章点击率前7条数据
     *
     * @return
     */
    List<ArticleRankVo> selectArticleRank();

    /**
     * 获取精选文章列表
     * @return
     */
    List<ArticlePopular> selectArticlePopular();

    /**
     * 根据id获取前台文章头部内容
     * @param id
     * @return
     */
    ArticleHeadVo selectFrontArticleHeadByIdVo(String id);
}
