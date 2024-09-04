package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysConfig;

import java.util.List;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22 11:12
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    /**
     * 查询参数配置信息
     *
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    SysConfig selectConfig(SysConfig config);

    /**
     * 查询参数配置列表
     *
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    SysConfig checkConfigKeyUnique(String configKey);

    /**
     * 新增参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    int updateConfig(SysConfig config);

    /**
     * 删除参数配置
     *
     * @param id 参数ID
     * @return 结果
     */
    int deleteConfigById(Long id);

    /**
     * 批量删除参数信息
     *
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    int deleteConfigByIds(Long[] ids);
}
