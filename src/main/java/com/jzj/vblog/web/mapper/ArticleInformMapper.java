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
 * @since 2022-07-23 11:12
 */
public interface ArticleInformMapper extends BaseMapper<ArticleInform> {

    /**
     * 后台查询文章
     */
    List<ArticleInform> selectArticleList(ArticleInform entity);

    /**
     * 前台分页查询文章列表
     */
    Page<ArticleVo> selectPageVo(@Param("page") Page<ArticleVo> page, @Param("tagId") String tagId,@Param("summaryId") String summaryId);

    /**
     * 根据id获取文章
     */
    ArticleAddVo selectArticleByIdVo(@Param("id") String id);

    ArticleFrontVo selectFrontArticleByIdVo(String id);

    /**
     * 获取文章点击率前7条数据
     */
    List<ArticleRankVo> selectArticleRank();

    /**
     * 获取点击率排行前3条文章
     */
    List<ArticlePopular> selectArticlePopular();

    /**
     * 获取所有上架置顶文章
     */
    List<SwiperVo> selectArticleTop();

    /**
     * 根据id获取前台文章头部内容
     */
    ArticleHeadVo selectFrontArticleHeadByIdVo(String id);

    /**
     * 根据关键字获取文章
     */
    List<SwiperVo> selectArticleListKw(@Param("kw") String kw);
}
