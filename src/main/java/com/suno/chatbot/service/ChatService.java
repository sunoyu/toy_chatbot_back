package com.suno.chatbot.service;

import com.suno.chatbot.domain.ChatRoom;
import com.suno.chatbot.domain.Message;
import com.suno.chatbot.dto.MessageDto;
import com.suno.chatbot.repository.ChatRoomRepository;
import com.suno.chatbot.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public List<MessageDto> getMessages(Long roomId) {
        return messageRepository.findByChatRoomIdOrderByCreatedAt(roomId)
                .stream()
                .map(MessageDto::from)
                .toList();
    }

    public MessageDto saveMessage(Long roomId, Message message) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 없습니다."));
        message.setChatRoom(chatRoom);
        Message saved = messageRepository.save(message);
        return MessageDto.from(saved);
    }

    public ChatRoom createRoom(ChatRoom room) {
        return chatRoomRepository.save(room);
    }

    public List<ChatRoom> getAllRooms() {
        return chatRoomRepository.findAll();
    }

}
