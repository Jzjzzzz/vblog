package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源站点 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26
 */
public interface WebsiteResourceService extends IService<WebsiteResource> {

    List<WebsiteResource> selectWebsiteList(WebsiteResource websiteResource);
}
