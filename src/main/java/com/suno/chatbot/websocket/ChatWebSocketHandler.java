package com.suno.chatbot.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String roomId = getRoomId(session);
        sessions.put(roomId, session);
        log.info(roomId);
        log.info(session.toString());
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String roomId = getRoomId(session);
        String userMessage = message.getPayload();

        // 1. 사용자의 메시지를 저장할 수도 있고
        // 2. 여기서 챗봇 응답 생성
        String botResponse = generateBotResponse(userMessage);

        // 예시: 간단한 응답 생성
        Map<String, String> response = Map.of(
                "role", "bot",
                "text", "안녕하세요! 무엇을 도와드릴까요?",
                "time", LocalDateTime.now().toString()
        );

        String json = new ObjectMapper().writeValueAsString(response);
        session.sendMessage(new TextMessage(json)); // ✅ 프론트에 응답 메시지 전송

        System.out.println("📤 응답 메시지 전송 완료");


        // 클라이언트에 응답
        WebSocketSession targetSession = sessions.get(roomId);
        log.info(targetSession.toString() );
        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage(botResponse));
        }
    }

    private String getRoomId(WebSocketSession session) {
        // 예: ws://localhost:8080/ws/chat?roomId=123
        String uri = session.getUri().toString();
        return uri.substring(uri.indexOf("roomId=") + 7);
    }

    private String generateBotResponse(String input) {
        if (input.contains("안녕")) return "안녕하세요! 무엇을 도와드릴까요?";
        return "질문을 이해하지 못했어요. 다시 한번 말씀해 주세요.";
    }
}

