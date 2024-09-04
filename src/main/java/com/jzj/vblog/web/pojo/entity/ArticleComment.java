package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 留言板
 * </p>
 *
 * @author Jzj
 * @since 2022-09-11 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleComment对象", description="留言板")
public class ArticleComment extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

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
