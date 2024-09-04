package com.jzj.vblog.web.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * 文章上一条下一条封装集合
 * </p>
 *
 * @author Jzj
 * @since 2022/9/6 16:03
 */
@Data
@ApiModel(description = "文章上一条下一条封装集合")
public class ArticleNextPreData {

    private String type;

    private String name;

    private String route;
}
