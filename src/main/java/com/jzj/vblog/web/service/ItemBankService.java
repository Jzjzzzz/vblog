package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.ItemBank;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 题库 服务类
 * </p>
 *
 * @author Jzj
 * @since 2023-06-07
 */
public interface ItemBankService extends IService<ItemBank> {

    /**
     * 分页查询
     *
     * @param itemBank 查询参数
     * @return 列表
     */
    List<ItemBank> pageList(ItemBank itemBank);

    /**
     * 新增题库
     *
     * @param itemBank 实体
     * @return 成功条数
     */
    int add(ItemBank itemBank);

    /**
     * 根据id查询单条记录
     *
     * @param id id
     * @return 实体
     */
    ItemBank selectById(String id);

    /**
     * 修改题库
     *
     * @param itemBank 实体
     * @return 变动条数
     */
    int modify(ItemBank itemBank);

    /**
     * 批量删除题库
     *
     * @param ids id数组
     * @return 变动条数
     */
    int deleteByIds(List<String> ids, HttpServletRequest request);

    /**
     * 随机获取一条题目
     *
     * @return 实体
     */
    ItemBank random();
}
