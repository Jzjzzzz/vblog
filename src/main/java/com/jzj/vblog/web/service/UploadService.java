package com.jzj.vblog.web.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:13
 * @Version 1.0
 * @Message:
 */
public interface UploadService {

    String uploadImg(MultipartFile photo, String name, HttpServletRequest request);
}
