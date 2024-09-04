package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源站点
 * </p>
 *
 * @author Jzj
 * @since 2022-07-26 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WebsiteResource对象", description = "资源站点")
public class WebsiteResource extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源名称")
    private String resourceName;

    @ApiModelProperty(value = "资源地址")
    private String resourceAddress;

    @ApiModelProperty(value = "资源图片")
    private String resourceImg;

    @ApiModelProperty(value = "资源分类")
    private String resourceType;

    @ApiModelProperty(value = "点击量")
    private Long clickRate;

    @ApiModelProperty(value = "资源描述")
    private String resourceDetail;
}
