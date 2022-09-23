package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 留言板
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleComment对象", description="留言板")
public class ArticleComment extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "是否为父节点")
    private String parentStatus;

    @ApiModelProperty(value = "文章id")
    private String articleId;

    @ApiModelProperty(value = "评论类型(0-留言板,1-文章评论)")
    private String commentType;

    @ApiModelProperty(value = "父节点id")
    private String parentId;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "所属省市")
    private String city;

    @ApiModelProperty(value = "审核状态(0-未审核,1-审核通过,2-审核未通过)")
    private String auditStatus;



}
