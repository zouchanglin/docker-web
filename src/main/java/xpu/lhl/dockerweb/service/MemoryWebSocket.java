package xpu.lhl.dockerweb.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@ServerEndpoint("/webSocket/memory")
public class MemoryWebSocket {
    private Session session;
    public static final CopyOnWriteArrayList<MemoryWebSocket> webSocketSet = Lists.newCopyOnWriteArrayList();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【WebSocket消息】有新的连接，总数：{}", webSocketSet.size());
    }

    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【WebSocket消息】连接断开，总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【WebSocket消息】 收到客户端的消息：{}", message);
    }

    public void sendTextMessage(String message){
        for(MemoryWebSocket webSocket: webSocketSet) {
            // log.info("【WebSocket消息】广播消息：message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}