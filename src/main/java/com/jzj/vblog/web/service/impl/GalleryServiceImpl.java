package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.aspectj.manager.AsyncFactory;
import com.jzj.vblog.aspectj.manager.AsyncManager;
import com.jzj.vblog.web.mapper.GalleryMapper;
import com.jzj.vblog.web.pojo.entity.Gallery;
import com.jzj.vblog.web.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 图集 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24 11:12
 */
@Service
public class GalleryServiceImpl extends ServiceImpl<GalleryMapper, Gallery> implements GalleryService {

    @Autowired
    private GalleryMapper galleryMapper;


    @Override
    public List<Gallery> selectGalleryList(Gallery gallery) {
        return galleryMapper.selectGalleryList(gallery);
    }

    @Override
    public int removeByIdImg(String id, HttpServletRequest request) {
        ArrayList<String> imgList = new ArrayList<>();
        Gallery gallery = galleryMapper.selectById(id);
        imgList.add(gallery.getImgAddress());
        //异步删除图片
        AsyncManager.me().execute(AsyncFactory.deleteBtnImg(imgList, request));
        return galleryMapper.deleteById(id);
    }
}
