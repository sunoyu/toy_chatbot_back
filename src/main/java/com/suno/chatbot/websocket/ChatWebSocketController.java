//package com.suno.chatbot.websocket;
//
//import com.suno.chatbot.domain.Message;
//import com.suno.chatbot.dto.MessageDto;
//import com.suno.chatbot.service.ChatService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.messaging.handler.annotation.DestinationVariable;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//@Controller
//@RequiredArgsConstructor
//public class ChatWebSocketController {
//
//    private final ChatService chatService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @MessageMapping("/chat.send/{roomId}")
//    public void sendMessage(@DestinationVariable Long roomId, Message message) {
//        // DB 저장
//        MessageDto saved = chatService.saveMessage(roomId, message);
//
//        // 브로드캐스트
//        messagingTemplate.convertAndSend("/topic/chat/" + roomId, saved);
//    }
//}
