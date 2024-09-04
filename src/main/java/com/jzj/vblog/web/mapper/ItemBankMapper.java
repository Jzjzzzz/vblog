package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.ItemBank;

import java.util.List;

/**
 * <p>
 * 题库 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2023-06-07 11:12
 */
public interface ItemBankMapper extends BaseMapper<ItemBank> {
    List<ItemBank> getPageList(ItemBank itemBank);

    ItemBank random();
}
