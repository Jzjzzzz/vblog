package com.jzj.vblog.web.controller;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.factory.UploadFactory;
import com.jzj.vblog.utils.constant.CacheConstants;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jzj
 * @Date 2022/7/26 16:17
 * @Version 1.0
 * @Message: 图片上传控制器
 */
@RestController
@RequestMapping("/api/upload")
@Api("图片上传控制器")
public class UploadController {



    @Autowired
    private SysConfigService sysConfigService;

    @Log(title = "文件管理",businessType = BusinessType.UPLOAD)
    @PostMapping("/uploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile photo,String name,HttpServletRequest request) {
        String url = ""; //回显URL
        UploadService uploadService = getUploadService();
        url = uploadService.uploadImg(photo, name, request);
        return R.ok("url",url);
    }


    @Log(title = "文件管理",businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteImg")
    public R deleteImg(@RequestParam("url") String url,HttpServletRequest request){
        UploadService uploadService = getUploadService();
        uploadService.deleteImg(url, request);
        return R.ok("删除成功");
    }

    /**
     * 获取文件上传方式实现类
     * @return
     */
    private UploadService getUploadService(){
        String enable = sysConfigService.selectConfigByKey(CacheConstants.A_LI_YUN_ENABLE_CODE); //获取阿里云oss是否开启
        if(enable.equals("true")){
            return UploadFactory.getUploadService(UploadCode.A_LI_YUN); //阿里云存储
        }else {
            return UploadFactory.getUploadService(UploadCode.LOCAL);  //本地存储
        }
    }

}
