package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.constant.UserConstants;
import com.jzj.vblog.utils.sign.DictUtils;
import com.jzj.vblog.utils.sign.StringUtils;
import com.jzj.vblog.web.mapper.SysDictDataMapper;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.service.SysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService {
    @Autowired
    private SysDictDataMapper dictDataMapper;

    @Override
    public List<SysDictData> selectDictDataList(SysDictData dictData) {
        return dictDataMapper.selectDictDataList(dictData);
    }

    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    @Override
    public int insertDictData(SysDictData data) {
        int row = dictDataMapper.insertDictData(data);
        if (row > 0) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    @Override
    public int updateDictData(SysDictData data) {
        int row = dictDataMapper.updateDictData(data);
        if (row > 0) {
            List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
            DictUtils.setDictCache(data.getDictType(), dictDatas);
        }
        return row;
    }

    @Override
    public boolean deleteDictDataByIds(Long[] dictCodes) {
        try {
            for (Long dictCode : dictCodes) {
                SysDictData data = selectDictDataById(dictCode);
                dictDataMapper.deleteDictDataById(dictCode);
                List<SysDictData> dictDatas = dictDataMapper.selectDictDataByType(data.getDictType());
                DictUtils.setDictCache(data.getDictType(), dictDatas);
            }
            return true;
        } catch (Exception e) {
            log.error("批量删除字典数据信息出错", e);
            return false;
        }
    }

    @Override
    public String checkDictDataUnique(SysDictData dict) {
        Long dictId = StringUtils.isNull(dict.getId()) ? -1L : dict.getId();
        SysDictData data = dictDataMapper.checkDictDataUnique(dict.getDictValue(), dict.getDictType());
        if (StringUtils.isNotNull(data) && data.getId().longValue() != dictId.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
