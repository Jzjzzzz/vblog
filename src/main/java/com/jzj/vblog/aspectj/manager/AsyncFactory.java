package com.jzj.vblog.aspectj.manager;

import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.Constants;
import com.jzj.vblog.utils.sign.*;
import com.jzj.vblog.web.pojo.entity.SysLogininfor;
import com.jzj.vblog.web.pojo.entity.SysOperLog;
import com.jzj.vblog.web.service.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.TimerTask;

/**
 * @Author Jzj
 * @Date 2022/7/22 11:12
 * @Version 1.0
 * @Message: 异步工厂（产生任务用）
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args)
    {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask()
        {
            @Override
            public void run()
            {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
                {
                    logininfor.setStatus(Constants.SUCCESS);
                }
                else if (Constants.LOGIN_FAIL.equals(status))
                {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(SysLogininforService.class).insertLogininfor(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(SysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

    /**
     * 批量删除图片
     * @param imgList 图片列表
     * @param request 请求
     * @return  任务task
     */
    public static TimerTask deleteBtnImg(List<String> imgList, HttpServletRequest request){
        return new TimerTask(){
            @Override
            public void run() {
                UploadService uploadService = UploadFactory.getUploadService(SpringUtils.getBean(SysConfigService.class));
                uploadService.deleteBtnImg(imgList,request);
            }
        };
    }

    /**
     * 发送邮件
     * @param to 目标email地址
     * @param subject 邮件主题
     * @param text 纯文本内容
     * @return  任务task
     */
    public static TimerTask sendMail(String to, String subject, String text){
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to,subject,text);
            }
        };
    }
}
