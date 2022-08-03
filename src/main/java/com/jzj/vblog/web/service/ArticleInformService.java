package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.ArticleInform;

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

    List<ArticleInform> selectList(ArticleInform entity);

    HashMap<String, Object> listPage(Long page, Long limit);
}
