package com.jzj.vblog.web.mapper;

import com.jzj.vblog.web.pojo.entity.MailSendLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 发送消息记录表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2023-01-31
 */
public interface MailSendLogMapper extends BaseMapper<MailSendLog> {

    Integer updateMailSendStatus(@Param("id") String id,@Param("status") Integer status);

    List<MailSendLog> getMailSendLogByStatus();

    void updateCount(@Param("id") String id,@Param("date") Date date);
}
