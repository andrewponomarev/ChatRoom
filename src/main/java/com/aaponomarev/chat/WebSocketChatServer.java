package com.aaponomarev.chat;

import com.aaponomarev.chat.model.Message;
import com.aaponomarev.chat.model.MessageType;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */

@Component
@ServerEndpoint(value = "/chat",
encoders = MessageEncoder.class,
decoders = MessageDecoder.class)
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message message) {
        onlineSessions.values().parallelStream().forEach(session -> {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (Exception e) {
                //log
            }
        });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen(Session session) {
        onlineSessions.put(session.getId(), session);
        Message message = new Message();
        message.setType(MessageType.OTHER);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, Message message) {
        message.setType(MessageType.SPEAK);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) {
        onlineSessions.remove(session.getId());
        Message message = new Message();
        message.setType(MessageType.OTHER);
        message.setOnlineCount(onlineSessions.size());
        sendMessageToAll(message);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
