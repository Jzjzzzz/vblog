package com.jzj.vblog.utils.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 阿里云OSS配置参数类
 * </p>
 *
 * @author Jzj
 * @since 2022/7/27 17:08
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class AliYunOssProperties implements InitializingBean {

    private String endpoint;

    private String keyId;

    private String keySecret;

    private String bucketName;

    public static String ENDPOINT;

    public static String KEY_ID;

    public static String KEY_SECRET;

    public static String BUCKET_NAME;

    /**
     * 当私有成员被赋值后，此方法自动被调用，从而初始化常量
     */
    @Override
    public void afterPropertiesSet() {
        ENDPOINT = endpoint;
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
