package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.Gallery;

import java.util.List;

/**
 * <p>
 * 图集 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24 11:12
 */
public interface GalleryMapper extends BaseMapper<Gallery> {

    List<Gallery> selectGalleryList(Gallery gallery);
}
