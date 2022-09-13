package com.jzj.vblog.web.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author Jzj
 * @Date 2022/9/13 9:39
 * @Version 1.0
 * @Message: 前台评论展示列表
 */

@Data
@ApiModel(description = "前台评论展示列表vo")
public class CommentFrontListVo {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "回复内容")
    private String message;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date date;

    @ApiModelProperty(value = "头像")
    private String userPhoto;

    @ApiModelProperty(value = "评论内容")
    private String response;

    @ApiModelProperty(value = "评论人昵称")
    private String responseName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "评论时间")
    private Date responseTime;

    @ApiModelProperty("IP地址中文")
    private String area;

}
