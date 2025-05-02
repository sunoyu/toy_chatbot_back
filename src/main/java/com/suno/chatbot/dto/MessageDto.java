package com.suno.chatbot.dto;

import com.suno.chatbot.domain.Message;

import java.time.LocalDateTime;

public record MessageDto(
        Long id,
        String role,
        String text,
        LocalDateTime createdAt
) {
    public static MessageDto from(Message message) {
        return new MessageDto(message.getId(), message.getRole(), message.getText(), message.getCreatedAt());
    }
}



