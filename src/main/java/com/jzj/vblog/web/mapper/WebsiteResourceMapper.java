package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.WebsiteResource;

import java.util.List;

/**
 * <p>
 * 资源站点 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26 11:12
 */
public interface WebsiteResourceMapper extends BaseMapper<WebsiteResource> {

    List<WebsiteResource> selectWebsiteList(WebsiteResource websiteResource);

    WebsiteResource checkwebSiteUnique(String resourceName);
}
