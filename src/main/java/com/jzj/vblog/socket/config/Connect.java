package com.jzj.vblog.socket.config;


import com.jzj.vblog.utils.sign.JwtUtils;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 连接数据存储
 * </p>
 *
 * @author Jzj
 * @since 2024/8/27 17:06
 */
public class Connect {

    private static final ConcurrentHashMap<Object, UUID> storage = new ConcurrentHashMap<>();

    /**
     * 保存链接
     *
     * @param token     token
     * @param sessionId 连接id
     */
    public static void save(String token, UUID sessionId) {
        String userId = JwtUtils.getSubject(token, JwtUtils.USERID);
        if (userId != null && sessionId != null) {
            storage.put(userId, sessionId);
        }
    }

    /**
     * 判断key是否存在于map中
     * @param id 用户id
     * @return 结果
     */
    public static boolean isContains(Object id){
        return storage.containsKey(id);
    }

    /**
     * 根据用户id获取客户端id
     * @param id 用户id
     * @return 客户端id
     */
    public static UUID getById(Object id){
        return storage.get(id);
    }

    /**
     * 客户端列表
     */
    public static Collection<UUID> list() {
        return storage.values();
    }
}
