package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;

/**
 * @Author Jzj
 * @Date 2022/8/4 10:36
 * @Version 1.0
 * @Message: 文章新增修改Vo
 */
@Data
@ApiModel(description="文章新增修改vo")
public class ArticleAddVo {

    /**
     * 文章id
     */
    private String id;


    /**
     * 文章标题
     */
    private String articleTitle;

    /**
     * 文章状态
     */
    private String status;

    /**
     * 文章分类
     */
    private String articleType;

    /**
     * 文章标签
     */
    private String articleTag;

    /**
     * 是否置顶
     */
    private String topStatus;

    /**
     * 是否原创
     */
    private String originStatus;

    /**
     * 是否开启评论
     */
    private String commentStatus;

    /**
     * 封面
     */
    private String logImg;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 内容
     */
    private String content;

    /**
     * 修改时间
     */
    private String updateTime;

    /**
     * 访问量
     */
    private String clickRate;
}
