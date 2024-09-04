package com.jzj.vblog.aspectj.manager;

import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.sign.AddressUtils;
import com.jzj.vblog.utils.sign.SpringUtils;
import com.jzj.vblog.web.pojo.entity.SysOperLog;
import com.jzj.vblog.web.service.EmailService;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.SysOperLogService;
import com.jzj.vblog.web.service.UploadService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.TimerTask;

/**
 * <p>
 * 异步工厂（产生任务用）
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
public class AsyncFactory {

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
