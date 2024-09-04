package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysTodo;

/**
 * <p>
 * 备忘录 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-14 11:12
 */
public interface SysTodoService extends IService<SysTodo> {

    /**
     * 唯一性检查
     *
     * @param content 事件内容
     * @return 校验结果
     */
    String checkUnique(String content);

    /**
     * 新增事件
     *
     * @param content 事件内容
     * @return 变更数量
     */
    int insertTodo(String content);


    /**
     * 修改备忘状态
     *
     * @return 变更数量
     */
    int updateTodo(SysTodo sysTodo);
}
