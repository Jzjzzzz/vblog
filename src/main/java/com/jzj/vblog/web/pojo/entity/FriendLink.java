package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 友情链接
 * </p>
 *
 * @author Jzj
 * @since 2022-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="FriendLink对象", description="友情链接")
public class FriendLink extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "链接名称")
    private String name;

    @ApiModelProperty(value = "链接")
    private String link;
}
