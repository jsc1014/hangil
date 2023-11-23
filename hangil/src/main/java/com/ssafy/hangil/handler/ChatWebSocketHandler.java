package com.ssafy.hangil.handler;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.ssafy.hangil.chat.model.ChatMessage;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();
    private final Gson gson = new Gson(); // Gson 인스턴스를 사용합니다.

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // 클라이언트가 보낸 메시지에서 userId를 포함하여 파싱합니다.
        ChatMessage chatMessage = gson.fromJson(message.getPayload(), ChatMessage.class);
        System.out.println(chatMessage);
        
        // 모든 세션에 메시지 전송
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(gson.toJson(chatMessage)));
            }
        }
    }
}

    
