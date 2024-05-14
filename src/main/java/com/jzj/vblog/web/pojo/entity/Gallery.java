package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图集
 * </p>
 *
 * @author Jzj
 * @since 2022-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Gallery对象", description="图集")
public class Gallery extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片地址")
    private String imgAddress;
}
