package com.jzj.vblog.factory;

import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文件上传工厂类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 13:57
 */
@Component
public class UploadFactory implements ApplicationContextAware {


    private static final String OPEN = "true";
    private static Map<UploadCode, UploadService> uploadServiceMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, UploadService> map = applicationContext.getBeansOfType(UploadService.class);
        uploadServiceMap = new HashMap<>();
        map.forEach((k, v) -> uploadServiceMap.put(v.getCode(), v));
    }

    public static UploadService getUploadService(SysConfigService sysConfigService) {
        String enable = sysConfigService.selectConfigByKey(CacheConstants.A_LI_YUN_ENABLE_CODE);
        if (OPEN.equals(enable)) {
            return uploadServiceMap.get(UploadCode.A_LI_YUN);
        } else {
            return uploadServiceMap.get(UploadCode.LOCAL);
        }
    }
}
