package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.aspectj.manager.AsyncFactory;
import com.jzj.vblog.aspectj.manager.AsyncManager;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.WebsiteResourceMapper;
import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.jzj.vblog.web.service.WebsiteResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源站点 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26 11:12
 */
@Service
public class WebsiteResourceServiceImpl extends ServiceImpl<WebsiteResourceMapper, WebsiteResource> implements WebsiteResourceService {

    @Autowired
    private WebsiteResourceMapper websiteResourceMapper;

    @Override
    public List<WebsiteResource> selectWebsiteList(WebsiteResource websiteResource) {
        return websiteResourceMapper.selectWebsiteList(websiteResource);
    }

    @Override
    public String checkWebsiteUnique(WebsiteResource websiteResource) {
        String websiteId = StringUtils.isNull(websiteResource.getId()) ? "-1L" : websiteResource.getId();
        WebsiteResource model = websiteResourceMapper.checkwebSiteUnique(websiteResource.getResourceName());
        if (StringUtils.isNotNull(model) && !model.getId().equals(websiteId)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertWebsite(WebsiteResource websiteResource) {
        //设置默认值
        websiteResource.setClickRate(0L);
        return websiteResourceMapper.insert(websiteResource);
    }

    @Override
    public WebsiteResource selectWebsiteById(String id) {
        return websiteResourceMapper.selectById(id);
    }

    @Override
    public int updateWebsite(WebsiteResource websiteResource) {
        return websiteResourceMapper.updateById(websiteResource);
    }

    @Override
    public int deleteWebsiteByIds(List<String> ids, HttpServletRequest request) {
        try {
            List<String> imgList = new ArrayList<>();
            //根据ids查询
            List<WebsiteResource> list = websiteResourceMapper.selectBatchIds(ids);
            list.forEach(s -> imgList.add(s.getResourceImg()));
            int result = websiteResourceMapper.deleteBatchIds(ids);
            //异步删除图片
            AsyncManager.me().execute(AsyncFactory.deleteBtnImg(imgList, request));
            return result;
        } catch (Exception e) {
            log.error("批量删除错误" + e);
            throw new RuntimeException(e);
        }
    }
}
