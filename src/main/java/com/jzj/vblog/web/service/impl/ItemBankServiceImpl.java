package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.web.mapper.ItemBankMapper;
import com.jzj.vblog.web.pojo.entity.ItemBank;
import com.jzj.vblog.web.service.ItemBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 题库 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2023-06-07 11:12
 */
@Service
public class ItemBankServiceImpl extends ServiceImpl<ItemBankMapper, ItemBank> implements ItemBankService {

    @Autowired
    private ItemBankMapper itemBankMapper;

    @Override
    public List<ItemBank> pageList(ItemBank itemBank) {
        return itemBankMapper.getPageList(itemBank);
    }

    @Override
    public int add(ItemBank itemBank) {
        return itemBankMapper.insert(itemBank);
    }

    @Override
    public ItemBank selectById(String id) {
        return itemBankMapper.selectById(id);
    }

    @Override
    public int modify(ItemBank itemBank) {
        return itemBankMapper.updateById(itemBank);
    }

    @Override
    public int deleteByIds(List<String> ids, HttpServletRequest request) {
        return itemBankMapper.deleteBatchIds(ids);
    }

    @Override
    public ItemBank random() {
        return itemBankMapper.random();
    }

}
