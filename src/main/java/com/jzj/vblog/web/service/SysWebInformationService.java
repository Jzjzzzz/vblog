package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysWebInformation;

/**
 * <p>
 * 网站基本信息 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-28
 */
public interface SysWebInformationService extends IService<SysWebInformation> {

    SysWebInformation selectWebInformationById();

    int updateWebInformation(SysWebInformation sysWebInformation);

    /**
     * 加载参数缓存数据
     */
    void loadingWebInformationCache();

    /**
     * 清空参数缓存数据
     */
    void clearInformationCache();

    /**
     * 重置参数缓存数据
     */
    void resetInformationCache();
}
