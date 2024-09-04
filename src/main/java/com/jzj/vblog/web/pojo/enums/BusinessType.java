package com.jzj.vblog.web.pojo.enums;

/**
 * <p>
 * 业务操作类型枚举
 * </p>
 *
 * @author Jzj
 * @since 2022/7/22 11:12
 */
public enum BusinessType {

    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,

    /**
     * 上传
     */
    UPLOAD,
}
