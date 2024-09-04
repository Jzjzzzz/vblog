package com.jzj.vblog.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 限制接口同ip访问次数
 * </p>
 *
 * @author Jzj
 * @since 2022/9/16 16:50
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentLimiting {

    /**
     * 限制时间 单位：毫秒(当前一分钟）
     */
    long time() default 60000;

    /**
     * 允许请求的次数
     */
    int value() default 5;
}
