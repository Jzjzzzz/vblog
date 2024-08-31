package com.jzj.vblog.socket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 * WebSocket启动类
 * </p>
 *
 * @author Jzj
 * @since 2024/8/27 15:31
 */
@Component
@Slf4j
public class WebSocketApplication implements CommandLineRunner {

    @Autowired
    private SocketIOServer socketServer;

    @Override
    public void run(String... args) {
        socketServer.start();
        System.out.println("WebSocket 服务器启动成功!");
    }
}
