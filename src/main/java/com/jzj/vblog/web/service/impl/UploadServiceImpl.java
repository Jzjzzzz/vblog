package com.jzj.vblog.web.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.jzj.vblog.utils.properties.AliYunOssProperties;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.uuid.IdUtils;
import com.jzj.vblog.web.service.SysConfigService;
import com.jzj.vblog.web.service.UploadService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:14
 * @Version 1.0
 * @Message:
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 普通文件上传
     * @param photo 文件
     * @param name 文件模块名
     * @param request
     * @return
     */
    @Override
    public String uploadImg(MultipartFile photo, String name, HttpServletRequest request) {
        File folder = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath()+"static"+'/'+name+'/'+ LocalDate.now().getYear()+'/');
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = photo.getOriginalFilename();
        String newName = IdUtils.fastSimpleUUID() + oldName.substring(oldName.lastIndexOf("."));
        try {
            //获取阿里云oss是否开启
            String enable = sysConfigService.selectConfigByKey("sys.oss.enable");
            if(enable.equals("true")){
                //TODO 走阿里云存储
            }
            // 文件保存
            photo.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String url = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() +"/"+name+"/"+LocalDate.now().getYear()+"/"+ newName;
            return url;
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR);
        }
    }

    /**
     * 文件上传至阿里云
     */
    private String aliYunUpload(InputStream inputStream, String module, String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                AliYunOssProperties.ENDPOINT,
                AliYunOssProperties.KEY_ID,
                AliYunOssProperties.KEY_SECRET);
        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        if(!ossClient.doesBucketExist(AliYunOssProperties.BUCKET_NAME)){
            //创建bucket
            ossClient.createBucket(AliYunOssProperties.BUCKET_NAME);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(AliYunOssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        //构建日期路径：avatar/2019/02/26/文件名
        String folder = new DateTime().toString("yyyy/MM/dd");
        //文件名：uuid.扩展名
        fileName = IdUtils.fastSimpleUUID() + fileName.substring(fileName.lastIndexOf("."));
        //文件根路径
        String key = module + "/" + folder + "/" + fileName;
        //文件上传至阿里云
        ossClient.putObject(AliYunOssProperties.BUCKET_NAME, key, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        //阿里云文件绝对路径
        return "https://" + AliYunOssProperties.BUCKET_NAME + "." + AliYunOssProperties.ENDPOINT + "/" + key;
    }

}
