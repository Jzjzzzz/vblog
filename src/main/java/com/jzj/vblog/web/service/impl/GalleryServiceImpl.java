package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.web.pojo.entity.Gallery;
import com.jzj.vblog.web.mapper.GalleryMapper;
import com.jzj.vblog.web.service.GalleryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 图集 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24
 */
@Service
public class GalleryServiceImpl extends ServiceImpl<GalleryMapper, Gallery> implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;

    @Autowired
    private SysConfigService sysConfigService;

    @Override
    public List<Gallery> selectGalleryList(Gallery gallery) {
        return galleryMapper.selectGalleryList(gallery);
    }

    private ThreadPoolTaskExecutor threadPoolTaskExecutor = SpringUtils.getBean("threadPoolTaskExecutor");

    @Override
    public int removeByIdImg(String id,HttpServletRequest request) {
        Gallery gallery = galleryMapper.selectById(id);
        CompletableFuture.runAsync(()->{
            UploadService uploadService = UploadFactory.getUploadService(sysConfigService);
            uploadService.deleteImg(gallery.getImgAddress(),request);
        },threadPoolTaskExecutor);
        return galleryMapper.deleteById(id);
    }
}
