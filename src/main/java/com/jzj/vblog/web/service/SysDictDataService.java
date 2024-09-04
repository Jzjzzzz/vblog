package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysDictData;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08 11:12
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictData> selectDictDataList(SysDictData dictData);

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    SysDictData selectDictDataById(Long dictCode);

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int insertDictData(SysDictData dictData);

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    int updateDictData(SysDictData dictData);

    /**
     * 批量删除字典数据信息
     *
     * @param dictCodes 需要删除的字典数据ID
     * @return 是否成功
     */
    boolean deleteDictDataByIds(Long[] dictCodes);

    /**
     * 校验字典数据是否唯一
     *
     * @param dict 实体
     * @return 校验结果
     */
    String checkDictDataUnique(SysDictData dict);
}
