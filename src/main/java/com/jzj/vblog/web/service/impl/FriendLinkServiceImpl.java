package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.pojo.entity.FriendLink;
import com.jzj.vblog.web.mapper.FriendLinkMapper;
import com.jzj.vblog.web.service.FriendLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 友情链接 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-09-10
 */
@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLink> selectLinkList(FriendLink friendLink) {
        List<FriendLink> list = friendLinkMapper.getPageList(friendLink);
        return list;
    }

    @Override
    public String checkLinkUnique(FriendLink friendLink) {
        String id = StringUtils.isNull(friendLink.getId()) ? "-1L" : friendLink.getId();
        FriendLink model = friendLinkMapper.checkLinkUnique(friendLink.getName());
        if (StringUtils.isNotNull(model) && !model.getId().equals(id)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public int insertLink(FriendLink friendLink) {
        return friendLinkMapper.insert(friendLink);
    }

    @Override
    public FriendLink selectLinkById(String id) {
        return friendLinkMapper.selectById(id);
    }

    @Override
    public int updateLink(FriendLink friendLink) {
        return friendLinkMapper.updateById(friendLink);
    }

    @Override
    public int deleteLinkByIds(List<String> ids, HttpServletRequest request) {
        return friendLinkMapper.deleteBatchIds(ids);
    }
}
