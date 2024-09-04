package com.jzj.vblog.web.pojo.entity;

import com.jzj.vblog.web.pojo.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 文章归档
 * </p>
 *
 * @author Jzj
 * @since 2022-08-11 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ArticleSummary对象", description="文章归档")
public class ArticleSummary extends CommonEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "归档名称")
    private String name;

    @ApiModelProperty(value = "归档图")
    private String banner;

    @ApiModelProperty(value = "是否显示在首页")
    private String topStatus;

    @ApiModelProperty(value = "作者")
    private String author;
}
