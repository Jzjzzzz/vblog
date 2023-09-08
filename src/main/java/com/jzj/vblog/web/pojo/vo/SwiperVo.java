package com.jzj.vblog.web.pojo.vo;

import lombok.Data;

/**
 * @Author Jzj
 * @Date 2023/9/7 9:26
 * @Version 1.0
 * @Message: 文章轮播图vo
 */
@Data
public class SwiperVo {

    /**
     * 文章id
     */
    private String id;

    /**
     * 图片
     */
    private String logImg;

    /**
     * 文章标题
     */
    private String title;
}
