package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.DictUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.pojo.entity.SysDictType;
import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.jzj.vblog.web.mapper.WebsiteResourceMapper;
import com.jzj.vblog.web.service.WebsiteResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 资源站点 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26
 */
@Service
public class WebsiteResourceServiceImpl extends ServiceImpl<WebsiteResourceMapper, WebsiteResource> implements WebsiteResourceService {

    @Autowired
    private WebsiteResourceMapper websiteResourceMapper;

    /**
     * 分页查询资源
     */
    @Override
    public List<WebsiteResource> selectWebsiteList(WebsiteResource websiteResource) {
        return websiteResourceMapper.selectWebsiteList(websiteResource);
    }

    /**
     * 校验资源是否唯一
     */
    @Override
    public String checkWebsiteUnique(WebsiteResource websiteResource) {
        String websiteId = StringUtils.isNull(websiteResource.getId()) ? "-1L" : websiteResource.getId();
        WebsiteResource model = websiteResourceMapper.checkwebSiteUnique(websiteResource.getResourceName());
        if (StringUtils.isNotNull(model) && !model.getId().equals(websiteId) )
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增资源
     */
    @Override
    public int insertWebsite(WebsiteResource websiteResource) {
        //设置默认值
        websiteResource.setClickRate(0L);
        return websiteResourceMapper.insert(websiteResource);
    }

    /**
     * 根据id获取资源
     * @param id
     * @return
     */
    @Override
    public WebsiteResource selectWebsiteById(String id) {
        return websiteResourceMapper.selectById(id);
    }

    @Override
    public int updateWebsite(WebsiteResource websiteResource) {
        return websiteResourceMapper.updateById(websiteResource);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Override
    public int deleteWebsiteByIds(List<String> ids) {
        return websiteResourceMapper.deleteBatchIds(ids);
    }
}
