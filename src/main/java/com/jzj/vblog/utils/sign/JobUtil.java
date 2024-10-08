package com.jzj.vblog.utils.sign;


import com.jzj.vblog.job.base.BaseJob;

/**
 * <p>
 * 定时任务反射工具类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:15
 */
public class JobUtil {

    /**
     * 根据全类名获取Job实例
     *
     * @param classname Job全类名
     * @return {@link BaseJob} 实例
     * @throws Exception 泛型获取异常
     */
    public static BaseJob getClass(String classname) throws Exception {
        Class<?> clazz = Class.forName(classname);
        return (BaseJob) clazz.newInstance();
    }
}
