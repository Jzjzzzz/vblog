package com.jzj.vblog.web.service.impl.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.jzj.vblog.utils.properties.AliYunOssProperties;
import com.jzj.vblog.utils.result.BusinessException;
import com.jzj.vblog.utils.result.ResponseEnum;
import com.jzj.vblog.utils.uuid.IdUtils;
import com.jzj.vblog.web.pojo.enums.UploadCode;
import com.jzj.vblog.web.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2022/7/27 17:14
 * @Version 1.0
 * @Message: 阿里云文件存储实现类
 */
@Slf4j
@Service
public class AliYunUploadServiceImpl implements UploadService {

    /**
     * 获取当前实现类编码
     *
     * @return
     */
    @Override
    public UploadCode getCode() {
        return UploadCode.A_LI_YUN;
    }

    @Override
    public String uploadImg(MultipartFile photo, String name, HttpServletRequest request) {
        OSS ossClient = null;
        try (InputStream inputStream = photo.getInputStream()) {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(AliYunOssProperties.ENDPOINT, AliYunOssProperties.KEY_ID, AliYunOssProperties.KEY_SECRET);
            String fileName = photo.getOriginalFilename();
            //判断oss实例是否存在：如果不存在则创建，如果存在则获取
            if (!ossClient.doesBucketExist(AliYunOssProperties.BUCKET_NAME)) {
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
            //阿里云文件绝对路径
            return "https://" + AliYunOssProperties.BUCKET_NAME + "." + AliYunOssProperties.ENDPOINT + "/" + key;
        } catch (Exception e) {
            log.error("阿里云oss存储上传错误：" + e.getMessage());
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR);
        } finally {
            // 关闭OSSClient。
            assert ossClient != null;
            ossClient.shutdown();
        }
    }

    @Retryable(value = Exception.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    @Override
    public boolean deleteImg(String url, HttpServletRequest request) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(AliYunOssProperties.ENDPOINT, AliYunOssProperties.KEY_ID, AliYunOssProperties.KEY_SECRET);
            //文件名（服务器上的文件路径）
            String host = "https://" + AliYunOssProperties.BUCKET_NAME + "." + AliYunOssProperties.ENDPOINT + "/";
            String objectName = url.substring(host.length());
            // 删除文件。
            ossClient.deleteObject(AliYunOssProperties.BUCKET_NAME, objectName);
            return true;
        } catch (Exception e) {
            log.error("阿里云oss存储删除错误：" + e.getMessage());
            throw new BusinessException(ResponseEnum.UPLOAD_DELETE_ERROR);
        } finally {
            // 关闭OSSClient。
            assert ossClient != null;
            ossClient.shutdown();
        }
    }

    @Retryable(value = Exception.class,maxAttempts = 2,backoff = @Backoff(delay = 2000,multiplier = 1.5))
    @Override
    public void deleteBtnImg(List<String> list, HttpServletRequest request) {
        OSS ossClient = null;
        try {
            //批量删除URL集合
            List<String> urls = new ArrayList<>();
            //文件名前缀
            String host = "https://" + AliYunOssProperties.BUCKET_NAME + "." + AliYunOssProperties.ENDPOINT + "/";
            /*封装文件绝对路径*/
            list.forEach(s -> {
                String url = s.substring(host.length());
                urls.add(url);
            });
            ossClient = new OSSClientBuilder().build(AliYunOssProperties.ENDPOINT, AliYunOssProperties.KEY_ID, AliYunOssProperties.KEY_SECRET);
            ossClient.deleteObjects(new DeleteObjectsRequest(AliYunOssProperties.BUCKET_NAME).withKeys(urls).withEncodingType("url"));
        } catch (Exception e) {
            log.error("阿里云oss存储批量删除错误：" + e.getMessage());
            throw new BusinessException(ResponseEnum.UPLOAD_DELETE_ERROR);
        } finally {
            // 关闭OSSClient
            assert ossClient != null;
            ossClient.shutdown();
        }
    }
}
