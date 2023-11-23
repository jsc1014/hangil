package com.ssafy.hangil.handler;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.TextMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final Map<String, String> chatRooms = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 연결이 성공적으로 맺어진 후, 사용자의 userId를 세션과 연결
        String userId = getUserName(session);
        userSessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JsonObject jsonMessage = JsonParser.parseString(message.getPayload()).getAsJsonObject();
        String type = jsonMessage.get("type").getAsString();

        if ("join".equals(type)) {
            String userId = getUserName(session);
            String targetUserId = jsonMessage.get("targetUserId").getAsString();

            // 채팅방 식별자 생성 (예: "userId1:userId2")
            String roomId = createRoomId(userId, targetUserId);

            // 채팅방에 사용자 추가
            chatRooms.put(userId, roomId);
        } else if ("message".equals(type)) {
            String userId = getUserName(session);
            String roomId = chatRooms.get(userId);
            sendMessageToRoom(roomId, message);
        }
    }

    private String createRoomId(String userId1, String userId2) {
        return userId1.compareTo(userId2) < 0 ? userId1 + ":" + userId2 : userId2 + ":" + userId1;
    }

    private void sendMessageToRoom(String roomId, TextMessage message) throws IOException {
        String[] userIds = roomId.split(":");
        for (String userId : userIds) {
            WebSocketSession session = userSessions.get(userId);
            if (session != null && session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }

    private String getUserName(WebSocketSession session) {
		return null;
        // 사용자 식별을 위한 로직 구현
        // 예를 들어, WebSocket 연결 URL의 쿼리 파라미터에서 userId 추출
    }
}

