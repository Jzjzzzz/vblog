package com.jzj.vblog.utils.constant;

/**
 * @Author Jzj
 * @Date 2022/7/22 10:02
 * @Version 1.0
 * @Message: 缓存的key 常量
 */
public class CacheConstants {
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "vblog:login_tokens:";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 阿里云存储开关 redis key
     */
    public static final String A_LI_YUN_ENABLE_CODE = "sys.oss.enable";

    /**
     * 网站基本信息
     */
    public static final String SYS_WEB_INFORMATION = "sys_web_information";

    /**
     * 文章标签
     */
    public static final String SYS_ARTICLE_TAG = "sys_article_tag";

    /**
     * 文章分类
     */
    public static final String SYS_ARTICLE_TYPE = "sys_article_type";

    /**
     *  邮箱通知开关
     */
    public static final String SYS_EMAIL_ENABLE = "sys_email_enable";

    /**
     * Email发送是否使用RabbitMQ
     */
    public static final String SYS_RABBIT_ENABLE = "sys_rabbit_enable";

    /**
     * 文章点击数缓存
     */
    public static final String VBLOG_ARTICLE_CLICK = "vblog:article:click:";
}
