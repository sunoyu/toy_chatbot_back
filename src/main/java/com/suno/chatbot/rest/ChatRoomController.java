package com.suno.chatbot.rest;

import com.suno.chatbot.domain.ChatRoom;
import com.suno.chatbot.domain.Message;
import com.suno.chatbot.dto.MessageDto;
import com.suno.chatbot.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/chatRooms")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatService chatService;

    @GetMapping
    public List<ChatRoom> getAllRooms() {
        return chatService.getAllRooms();
    }

    @PostMapping
    public ChatRoom createRoom(@RequestBody ChatRoom room) {
        return chatService.createRoom(room);
    }

    @GetMapping("/{roomId}/messages")
    public List<MessageDto> getMessages(@PathVariable Long roomId) {
        return chatService.getMessages(roomId);
    }

    @PostMapping("/{roomId}/messages")
    public MessageDto addMessage(@PathVariable Long roomId, @RequestBody Message message) {
        return chatService.saveMessage(roomId, message);
    }

}
