package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.WebsiteResource;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 资源站点 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26
 */
public interface WebsiteResourceService extends IService<WebsiteResource> {

    /**
     * 查询资源站点列表
     *
     * @param websiteResource 查询条件
     * @return 列表
     */
    List<WebsiteResource> selectWebsiteList(WebsiteResource websiteResource);

    /**
     * 唯一性校验
     *
     * @param websiteResource 实体
     * @return 唯一性校验结果
     */
    String checkWebsiteUnique(WebsiteResource websiteResource);

    /**
     * 保存资源站点
     *
     * @param websiteResource 实体
     * @return 变动条数
     */
    int insertWebsite(WebsiteResource websiteResource);

    /**
     * 根据id查询资源站点
     *
     * @param id id
     * @return 实体
     */
    WebsiteResource selectWebsiteById(String id);

    /**
     * 更新资源站点
     *
     * @param websiteResource 实体
     * @return 变动条数
     */
    int updateWebsite(WebsiteResource websiteResource);

    /**
     * 删除资源站点
     *
     * @param ids     ids
     * @param request 请求
     * @return 变动条数
     */
    int deleteWebsiteByIds(List<String> ids, HttpServletRequest request);
}
