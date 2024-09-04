package com.jzj.vblog.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.vblog.web.pojo.entity.SysNotice;

import java.util.List;

/**
 * <p>
 * 通知公告表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22 11:12
 */
public interface SysNoticeMapper extends BaseMapper<SysNotice> {

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNotice> selectNoticeList(SysNotice notice);
}
