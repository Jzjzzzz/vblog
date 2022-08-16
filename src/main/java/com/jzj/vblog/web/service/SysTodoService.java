package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysTodo;

/**
 * <p>
 * 备忘录 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-14
 */
public interface SysTodoService extends IService<SysTodo> {

    /**
     * 唯一性检查
     *
     * @param content 事件内容
     * @return
     */
    String checkUnique(String content);

    /**
     * 新增事件
     *
     * @param content 事件内容
     * @return
     */
    int insertTodo(String content);


    /**
     * 修改备忘状态
     *
     * @return
     */
    int updateTodo(SysTodo sysTodo);
}
