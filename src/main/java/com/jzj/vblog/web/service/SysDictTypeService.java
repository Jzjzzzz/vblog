package com.jzj.vblog.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.jzj.vblog.web.pojo.entity.SysDictType;

import java.util.List;

/**
 * <p>
 * 字典类型表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08 11:12
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 查询字典类型列表
     *
     * @param dictType 字典类型信息
     * @return 列表
     */
    List<SysDictType> selectDictTypeList(SysDictType dictType);

    /**
     * 根据id查询字典类型
     *
     * @param dictId 字典id
     * @return 字典类型
     */
    SysDictType selectDictTypeById(Long dictId);

    /**
     * 查询字典类型列表
     *
     * @param dictType 字典类型信息
     * @return 列表
     */
    List<SysDictData> selectDictDataByType(String dictType);

    /**
     * 唯一性校验
     *
     * @param dict 实体
     * @return 校验结果
     */
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
    boolean deleteDictTypeByIds(Long[] dictIds);


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
