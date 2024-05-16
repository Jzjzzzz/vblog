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


    /**
     * 查询友情链接列表
     *
     * @param friendLink 查询条件
     * @return 列表
     */
    List<FriendLink> selectLinkList(FriendLink friendLink);

    /**
     * 唯一性校验
     *
     * @param friendLink 实体
     * @return 唯一性校验结果
     */
    String checkLinkUnique(FriendLink friendLink);

    /**
     * 保存友情链接
     *
     * @param friendLink 实体
     * @return 变动条数
     */
    int insertLink(FriendLink friendLink);

    /**
     * 根据id查询友情链接详情
     *
     * @param id id
     * @return 实体
     */
    FriendLink selectLinkById(String id);

    /**
     * 更新友情链接
     *
     * @param friendLink 实体
     * @return 变动条数
     */
    int updateLink(FriendLink friendLink);

    /**
     * 删除友情链接
     *
     * @param ids     id列表
     * @param request 请求
     * @return 变动条数
     */
    int deleteLinkByIds(List<String> ids, HttpServletRequest request);
}
