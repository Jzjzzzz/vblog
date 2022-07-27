package com.jzj.vblog.web.service.impl.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.jzj.vblog.utils.properties.AliYunOssProperties;
import com.jzj.vblog.utils.uuid.IdUtils;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.UploadService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:14
 * @Version 1.0
 * @Message: 阿里云文件存储实现类
 */
@Service
public class AliYunUploadServiceImpl implements UploadService {
    /**
     * 获取当前实现类编码
     * @return
     */
    @Override
    public UploadCode getCode() {
        return UploadCode.A_LI_YUN;
    }

    @Override
    public String uploadImg(MultipartFile photo, String name, HttpServletRequest request) {
        try {
            InputStream inputStream = photo.getInputStream();
            String fileName = photo.getOriginalFilename();
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
            String key = name + "/" + folder + "/" + fileName;
            //文件上传至阿里云
            ossClient.putObject(AliYunOssProperties.BUCKET_NAME, key, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //阿里云文件绝对路径
            return "https://" + AliYunOssProperties.BUCKET_NAME + "." + AliYunOssProperties.ENDPOINT + "/" + key;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
