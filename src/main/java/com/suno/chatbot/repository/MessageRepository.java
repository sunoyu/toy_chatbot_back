package com.suno.chatbot.repository;

import com.suno.chatbot.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoomIdOrderByCreatedAt(Long chatRoomId);
}
