package com.jzj.vblog.web.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jzj.vblog.web.pojo.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22 11:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SysLogininfor对象", description = "系统访问记录")
public class SysLogininfor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    @ApiModelProperty(value = "用户账号")
    private String userName;

    @ApiModelProperty(value = "登录IP地址")
    private String ipaddr;

    @ApiModelProperty(value = "登录地点")
    private String loginLocation;

    @ApiModelProperty(value = "浏览器类型")
    private String browser;

    @ApiModelProperty(value = "操作系统")
    private String os;

    @ApiModelProperty(value = "登录状态（0成功 1失败）")
    private String status;

    @ApiModelProperty(value = "提示消息")
    private String msg;

    @ApiModelProperty(value = "访问时间")
    private Date loginTime;


}
