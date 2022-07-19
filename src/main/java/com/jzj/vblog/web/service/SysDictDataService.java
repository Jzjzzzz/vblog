package com.jzj.vblog.web.service;

import com.jzj.vblog.web.pojo.entity.SysDictData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
public interface SysDictDataService extends IService<SysDictData> {

    List<SysDictData> selectDictDataList(SysDictData dictData);
}
