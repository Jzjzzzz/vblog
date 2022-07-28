package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.enums.UploadCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:13
 * @Version 1.0
 * @Message:
 */
public interface UploadService {

    /**
     * 查询实现类编码
     * @return 编码
     */
    UploadCode getCode();

    /**
     * 文件上传
     * @param photo
     * @param name
     * @param request
     * @return
     */
    String uploadImg(MultipartFile photo, String name, HttpServletRequest request);

    /**
     * 文件删除
     * @param url
     * @param request
     * @return
     */
    boolean deleteImg( String url,HttpServletRequest request);
}
