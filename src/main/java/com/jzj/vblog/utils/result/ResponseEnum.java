package com.jzj.vblog.utils.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ResponseEnum {


    //-1xx 服务器错误
    BAD_SQL_GRAMMAR_ERROR(-101, "sql语法错误"),
    SERVLET_ERROR(-102, "servlet请求异常"), //-2xx 参数校验
    UPLOAD_ERROR(-103, "文件上传错误"),
    EXPORT_DATA_ERROR(104, "数据导出失败"),
    UPLOAD_DELETE_ERROR(105, "文件删除失败"),
    UPLOAD_DELETE_NOT(106, "文件不存在"),
    REQUEST_UPPER_LIMIT(107,"接口请求超过次数"),


    //-2xx 参数校验
    Model_NULL_ERROR(-201, "数据不存在"),
    MOBILE_NULL_ERROR(-202, "手机号码不能为空"),
    MOBILE_ERROR(-203, "手机号码不正确"),
    PASSWORD_NULL_ERROR(204, "密码不能为空"),
    CODE_NULL_ERROR(205, "验证码不能为空"),
    CODE_ERROR(206, "验证码错误"),
    MOBILE_EXIST_ERROR(207, "手机号已被注册"),
    LOGIN_MOBILE_ERROR(208, "用户不存在"),
    LOGIN_PASSWORD_ERROR(209, "密码错误"),
    LOGIN_LOKED_ERROR(210, "用户被锁定"),
    LOGIN_AUTH_ERROR(-211, "未登录"),
    LOGIN_USER_ERROR(-212,"账号密码错误"),
    LOGIN_USER_NULL_ERROR(-213,"账号不能为空"),
    LOGIN_PASSWORD_NULL_ERROR(-214,"密码不能为空"),
    SUMMARY_TOP_NUMBER_MAX(-215,"归档置顶数置顶数超过最大值3");
    ;

    private Integer code;
    private String message;
}
