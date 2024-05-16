package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.vo.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章基础信息 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-23
 */
public interface ArticleInformService extends IService<ArticleInform> {

    /**
     * 后台分页文章列表
     *
     * @param entity 查询条件
     * @return 列表
     */
    List<ArticleInform> selectList(ArticleInform entity);

    /**
     * 前台分页显示文章
     *
     * @param query 查询条件
     * @return 列表
     */
    HashMap<String, Object> listPage(Map<String, Object> query);

    /**
     * 新增文章
     *
     * @param vo 文章新增修改Vo
     * @return 修改条数
     */
    int articleAdd(ArticleAddVo vo);

    /**
     * 根据ID获取文章详细
     *
     * @param id 文章id
     * @return 归档列表，文章详细
     */
    ArticleAddVo getArticleById(String id);

    /**
     * 根据ID修改文章
     *
     * @param vo 文章新增修改Vo
     * @return 修改条数
     */
    int updateArticleById(ArticleAddVo vo);

    /**
     * 根据ID删除文章
     *
     * @param ids     id数组
     * @param request 请求
     * @return 是否成功
     */
    boolean deleteArticleById(String[] ids, HttpServletRequest request);

    /**
     * 前台根据文章Id获取文章信息
     *
     * @param id 文章id
     * @return 文章详细
     */
    ArticleFrontVo getFrontArticleById(String id);

    /**
     * 后台文章排行榜列表
     *
     * @return 列表
     */
    List<ArticleRankVo> getRank();

    /**
     * 文章点赞
     *
     * @param id 文章id
     */
    void getByIdLike(String id);


    /**
     * 根据id修改文章归档
     *
     * @param id   文章id
     * @param type 归档类型
     * @return 是否成功
     */
    boolean updateSummaryById(String id, String summaryId, String type);


    /**
     * 根据Id获取文章头部信息
     *
     * @return 头部信息
     */
    ArticleHeadVo getHeadById(String id);

    /**
     * 获取文章轮播图列表
     *
     * @return 列表
     */
    List<SwiperVo> getSwiperList();

    /**
     * 根据关键字获取10条相似博客
     *
     * @param kw 关键字
     * @return 列表
     */
    List<SwiperVo> getArticleListKw(String kw);
}
