package com.suno.chatbot.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩, 메시지를 조회할 때 chatRoom은 바로 로딩하지 않는다 → 나중에 필요할 때 실제 DB에서 가져오도록 함 (성능 최적화용)
    private ChatRoom chatRoom;

    private String role;
    private String text;
    private LocalDateTime createdAt = LocalDateTime.now();
}
