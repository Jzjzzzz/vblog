package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.FriendLink;

import java.util.List;

/**
 * <p>
 * 友情链接 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-09-10 11:12
 */
public interface FriendLinkMapper extends BaseMapper<FriendLink> {

    List<FriendLink> getPageList(FriendLink friendLink);

    FriendLink checkLinkUnique(String name);
}
