package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;

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
     * @param entity
     * @return
     */
    List<ArticleInform> selectList(ArticleInform entity);

    /**
     * 前台分页显示文章
     * @return
     */
    HashMap<String, Object> listPage(Map<String,Object> queryMap);

    /**
     * 新增文章
     * @param vo
     * @return
     */
    int articleAdd(ArticleAddVo vo);

    /**
     * 根据ID获取文章详细
     * @param id
     * @return
     */
    Map<String,Object> getArticleById(String id);

    /**
     * 根据ID修改文章
     * @return
     */
    int updateArticleById(ArticleAddVo vo);

    /**
     * 根据ID删除文章
     */
    void deleteArticleById(String [] ids,HttpServletRequest request);

    /**
     * 前台根据文章Id获取文章信息
     * @param id
     * @return
     */
    ArticleAddVo getFrontArticleById(String id);
}
