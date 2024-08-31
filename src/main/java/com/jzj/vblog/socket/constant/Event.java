package com.jzj.vblog.socket.constant;

/**
 * <p>
 * 事件常量
 * </p>
 *
 * @author Jzj
 * @since 2024/8/27 17:23
 */
public interface Event {

    /**
     * 聊天事件
     */
    String CHAT = "chat";

    /**
     * 广播消息
     */
    String BROADCAST = "broadcast";

    /**
     * 群聊
     */
    String GROUP = "group";

    /**
     * 加入群聊
     */
    String JOIN = "join";

    /**
     * 登录
     */
    String LOGIN = "login";
}
