package com.jzj.vblog.web.service.impl;

import com.jzj.vblog.socket.handler.MessageEventHandler;
import com.jzj.vblog.web.pojo.entity.SysNotice;
import com.jzj.vblog.web.mapper.SysNoticeMapper;
import com.jzj.vblog.web.service.SysNoticeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@Service
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

    @Autowired
    private SysNoticeMapper noticeMapper;

    @Autowired
    private MessageEventHandler handler;

    @Override
    public SysNotice selectNoticeById(String noticeId) {
        return noticeMapper.selectById(noticeId);
    }

    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return noticeMapper.selectNoticeList(notice);
    }

    @Override
    public int insertNotice(SysNotice notice) {
        handler.broadcast(notice.getNoticeContent());
        return noticeMapper.insert(notice);
    }

    @Override
    public int updateNotice(SysNotice notice) {
        return noticeMapper.updateById(notice);
    }

    @Override
    public int deleteNoticeByIds(String[] noticeIds) {
        return noticeMapper.deleteBatchIds(Arrays.asList(noticeIds));
    }
}
