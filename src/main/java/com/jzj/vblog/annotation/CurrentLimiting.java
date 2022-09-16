package com.jzj.vblog.annotation;

import java.lang.annotation.*;

/**
 * @Author Jzj
 * @Date 2022/9/16 16:50
 * @Version 1.0
 * @Message: 限制接口同ip访问次数
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimiting {
    long time() default 60000; // 限制时间 单位：毫秒(当前一分钟）
    int value() default 5; // 允许请求的次数
}
