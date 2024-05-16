package com.jzj.vblog.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.vblog.utils.sign.DateUtils;
import com.jzj.vblog.web.mapper.SysCountMapper;
import com.jzj.vblog.web.pojo.entity.SysCount;
import com.jzj.vblog.web.pojo.vo.BeforeDayCountVo;
import com.jzj.vblog.web.service.SysCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 统计归档 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
@Service
public class SysCountServiceImpl extends ServiceImpl<SysCountMapper, SysCount> implements SysCountService {

    @Autowired
    private SysCountMapper sysCountMapper;

    @Override
    public SysCount getCount() {
        SysCount sysCount;
        sysCount = sysCountMapper.getBeforeDayCount();
        if (sysCount == null) {
            sysCount = new SysCount();
            sysCount.setArticleCount(0L);
            sysCount.setLikeCount(0L);
            sysCount.setClickCount(0L);
            sysCount.setSummaryCount(0L);
        }
        return sysCount;
    }

    @Override
    public BeforeDayCountVo getSevenCount() {
        BeforeDayCountVo beforeDayCountVo = new BeforeDayCountVo();
        List<Long> clickDayCounts = new ArrayList<>();
        List<Long> likeDayCounts = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<SysCount> list = sysCountMapper.getSevenCount();
        for (SysCount sysCount : list) {
            clickDayCounts.add(sysCount.getClickDayCount());
            likeDayCounts.add(sysCount.getLikeDayCount());
            dateList.add(DateUtils.dateToStrLong(sysCount.getCreateTime()));
        }
        beforeDayCountVo.setLikeDayCounts(likeDayCounts);
        beforeDayCountVo.setClickDayCounts(clickDayCounts);
        beforeDayCountVo.setDateList(dateList);
        return beforeDayCountVo;
    }
}
