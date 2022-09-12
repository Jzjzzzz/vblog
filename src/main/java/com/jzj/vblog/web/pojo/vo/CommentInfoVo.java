package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "评论详情vo")
public class CommentInfoVo {

    @ApiModelProperty(value = "父节点id")
    private String id;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "回复内容")
    private String reply;

    @ApiModelProperty(value = "子id")
    private String sid;
}
