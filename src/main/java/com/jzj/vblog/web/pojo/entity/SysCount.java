package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 统计归档
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysCount对象", description = "统计归档")
public class SysCount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "文章数")
    private Long articleCount;

    @ApiModelProperty(value = "归档数")
    private Long summaryCount;

    @ApiModelProperty(value = "用户数")
    private Long userCount;

    @ApiModelProperty(value = "点击数")
    private Long clickCount;

    @ApiModelProperty(value = "点赞数")
    private Long likeCount;


}
