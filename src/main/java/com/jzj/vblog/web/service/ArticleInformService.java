package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleInform;
import com.jzj.vblog.web.pojo.vo.ArticleAddVo;

import java.util.HashMap;
import java.util.List;

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
     * @param page
     * @param limit
     * @return
     */
    HashMap<String, Object> listPage(Long page, Long limit);

    /**
     * 新增文章
     * @param vo
     * @return
     */
    int articleAdd(ArticleAddVo vo);

    ArticleAddVo getArticleById(String id);
}
