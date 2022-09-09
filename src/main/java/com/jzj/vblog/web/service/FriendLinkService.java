package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 友情链接 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-10
 */
public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLink> selectLinkList(FriendLink friendLink);

    String checkLinkUnique(FriendLink friendLink);

    int insertLink(FriendLink friendLink);

    FriendLink selectLinkById(String id);

    int updateLink(FriendLink friendLink);

    int deleteLinkByIds(List<String> ids, HttpServletRequest request);
}
