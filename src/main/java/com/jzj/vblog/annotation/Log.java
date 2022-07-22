package com.jzj.vblog.annotation;

import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.enums.OperatorType;

import java.lang.annotation.*;

/**
 * @Author Jzj
 * @Date 2022/7/22 11:01
 * @Version 1.0
 * @Message: 自定义操作日志记录注解
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    public OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;
}
