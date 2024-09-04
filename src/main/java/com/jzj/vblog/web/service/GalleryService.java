package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.Gallery;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 图集 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24 11:12
 */
public interface GalleryService extends IService<Gallery> {

    /**
     * 查询图集列表
     *
     * @param gallery 查询条件
     * @return 列表
     */
    List<Gallery> selectGalleryList(Gallery gallery);

    /**
     * 删除图集
     *
     * @param id      图片id
     * @param request 请求
     * @return 变动条数
     */
    int removeByIdImg(String id, HttpServletRequest request);
}
