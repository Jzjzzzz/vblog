package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.WebsiteResourceMapper;
import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import com.jzj.vblog.web.service.WebsiteResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    private SysConfigService sysConfigService;

    @Autowired
    private WebsiteResourceMapper websiteResourceMapper;

    private ExecutorService cacheThreadPool= Executors.newFixedThreadPool(1024);

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
    public int deleteWebsiteByIds(List<String> ids, HttpServletRequest request) {
        //根据ids查询
        List<WebsiteResource> list = websiteResourceMapper.selectBatchIds(ids);
        int result = websiteResourceMapper.deleteBatchIds(ids);
        //多线程执行批量删除图片操作
        CompletableFuture.runAsync(()->{
            UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
            uploadService.deleteBtnImg(list,request); //批量删除图片
        },cacheThreadPool);
        return result;
    }


}
