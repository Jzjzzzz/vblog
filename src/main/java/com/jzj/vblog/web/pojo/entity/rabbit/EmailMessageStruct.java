package com.jzj.vblog.web.pojo.entity.rabbit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 邮箱发送消息体
 * </p>
 *
 * @author Jzj
 * @since 2023/1/16 15:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailMessageStruct  implements Serializable {

    private static final long serialVersionUID = 392365881428311040L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 回复消息
     */
    private String reply;
}
