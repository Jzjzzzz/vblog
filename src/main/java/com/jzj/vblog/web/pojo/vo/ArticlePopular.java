package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 精选文章vo
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@Data
@ApiModel(description = "精选文章vo")
public class ArticlePopular {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "文章标题")
    private String articleTitle;

    @ApiModelProperty(value = "标题图")
    private String logImg;
}
