package com.jzj.vblog.utils.sign;

/**
 * @Author Jzj
 * @Date 2022/7/22 11:17
 * @Version 1.0
 * @Message: 处理并记录日志文件
 */
public class LogUtils {
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
