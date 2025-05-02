package com.suno.chatbot.dto;

import com.suno.chatbot.domain.ChatRoom;

import java.time.LocalDateTime;

public record ChatRoomDto(
        Long id,
        String name,
        LocalDateTime createdAt
) {
    public static ChatRoomDto from(ChatRoom room) {
        return new ChatRoomDto(room.getId(), room.getName(), room.getCreatedAt());
    }
}
