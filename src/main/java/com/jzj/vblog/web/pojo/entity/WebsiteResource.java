package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="WebsiteResource对象", description="资源站点")
public class WebsiteResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源站点表id")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private Long id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "状态")
    private String status;

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


}
