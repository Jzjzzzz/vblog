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

@Component
public class UploadFactory implements ApplicationContextAware {
    private static Map<UploadCode, UploadService> uploadServiceMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, UploadService> map = applicationContext.getBeansOfType(UploadService.class);
        uploadServiceMap = new HashMap<>();
        map.forEach((k,v)-> uploadServiceMap.put(v.getCode(),v));
    }

    public static <T extends UploadService> T getUploadService(SysConfigService sysConfigService){
        String enable = sysConfigService.selectConfigByKey(CacheConstants.A_LI_YUN_ENABLE_CODE); //获取阿里云oss是否开启
        if(enable.equals("true")){
            return (T)uploadServiceMap.get(UploadCode.A_LI_YUN); //阿里云存储
        }else {
            return (T)uploadServiceMap.get(UploadCode.LOCAL);  //本地存储
        }

    }
}
