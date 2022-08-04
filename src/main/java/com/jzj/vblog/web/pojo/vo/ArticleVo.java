package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author Jzj
 * @Date 2022/7/29 17:08
 * @Version 1.0
 * @Message: 前台文章vo
 */
@Data
@ApiModel(description="前台文章vo")
public class ArticleVo {
    //文章详细id
    private String id;

    //是否置顶
    private Boolean isTop;

    //图片
    private String logImg;

    //标签id
    private String tagIds;

    //轮播图
    private String [] banner;

    //是否热门
    private Boolean isHot;

    //创建时间
    private String pubTime;

    //标题
    private String title;

    //概要
    private String summary;

    //内容
    private String content;

    //点击数
    private Integer viewsCount;

    //评论数
    private Integer commentsCount;

    //分类名称
    private String typeName;

    //标签名称数组
    private String [] tagNameArray;
}
