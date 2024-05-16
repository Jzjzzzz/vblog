package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.enums.UploadCode;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:13
 * @Version 1.0
 * @Message:
 */
public interface UploadService {

    /**
     * 查询实现类编码
     *
     * @return 编码
     */
    UploadCode getCode();

    /**
     * 文件上传
     *
     * @param photo   文件
     * @param name    文件名
     * @param request 请求
     * @return 文件路径
     */
    String uploadImg(MultipartFile photo, String name, HttpServletRequest request);

    /**
     * 文件删除
     *
     * @param url     文件路径
     * @param request 请求
     * @return 删除结果
     */
    boolean deleteImg(String url, HttpServletRequest request);

    /**
     * 文件批量删除
     *
     * @param list    文件路径集合
     * @param request 请求
     */
    void deleteBtnImg(List<String> list, HttpServletRequest request);
}
