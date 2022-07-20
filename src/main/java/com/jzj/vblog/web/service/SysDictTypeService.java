package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.entity.SysDictType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
public interface SysDictTypeService extends IService<SysDictType> {

    List<SysDictType> selectDictTypeList(SysDictType dictType);

    SysDictType selectDictTypeById(Long dictId);

    List<SysDictData> selectDictDataByType(String dictType);

    String checkDictTypeUnique(SysDictType dict);


    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int insertDictType(SysDictType dictType);

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    int updateDictType(SysDictType dictType);

    /**
     * 批量删除字典信息
     *
     * @param dictIds 需要删除的字典ID
     */
    void deleteDictTypeByIds(Long[] dictIds);


    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();


    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
     List<SysDictType> selectDictTypeAll();
}
