package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.Gallery;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 图集 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24
 */
public interface GalleryService extends IService<Gallery> {

    List<Gallery> selectGalleryList(Gallery gallery);

    int removeByIdImg(String id,HttpServletRequest request);
}
