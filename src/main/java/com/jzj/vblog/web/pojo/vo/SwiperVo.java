package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章轮播图vo
 * </p>
 *
 * @author Jzj
 * @since 2023/9/7 9:26
 */
@Data
@ApiModel(description = "文章轮播图vo")
public class SwiperVo {

    @ApiModelProperty(value = "文章id")
    private String id;

    @ApiModelProperty(value = "图片")
    private String logImg;

    @ApiModelProperty(value = "文章标题")
    private String title;
}
