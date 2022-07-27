package com.jzj.vblog.web.controller;

import com.jzj.vblog.annotation.Log;
import com.jzj.vblog.utils.result.R;
import com.jzj.vblog.web.pojo.enums.BusinessType;
import com.jzj.vblog.web.service.UploadService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

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
    private UploadService uploadService;

    @Log(title = "文件管理",businessType = BusinessType.UPLOAD)
    @PostMapping("/uploadImg")
    public R uploadImg(@RequestParam("file") MultipartFile photo,String name,HttpServletRequest request) {
        String url = uploadService.uploadImg(photo,name,request);
        return R.ok("url",url);
    }


    @Log(title = "文件管理",businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteImg")
    public R deleteImg(@RequestParam("url") String url,HttpServletRequest request){
        //获取需要截取部分
        String s = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() +"/";
        String substring = url.substring(s.length());
        //文件位置
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static"+'/'+substring;
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
               return R.ok("删除成功");
            } else {
               return R.error("删除错误");
            }
        } else {
           return R.error("文件不存在");
        }
    }

}
