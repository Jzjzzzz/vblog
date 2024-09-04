package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jzj.vblog.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 网站基本信息
 * </p>
 *
 * @author Jzj
 * @since 2022-07-28 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysWebInformation对象", description = "网站基本信息")
public class SysWebInformation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "网站名称")
    private String webName;

    @ApiModelProperty(value = "网站详情")
    private String webDetails;

    @ApiModelProperty(value = "头像")
    private String webAvatar;

    @ApiModelProperty(value = "首页背景图")
    private String homeBanner;

    @ApiModelProperty(value = "文章背景图")
    private String articleBanner;


    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;

    @ApiModelProperty(value = "github")
    private String github;

    @ApiModelProperty(value = "gitee")
    private String gitee;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "微信二维码")
    private String wechatImg;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "评论头像")
    private String commentImg;

    @ApiModelProperty(value = "备案号")
    private String recordNumber;


}
