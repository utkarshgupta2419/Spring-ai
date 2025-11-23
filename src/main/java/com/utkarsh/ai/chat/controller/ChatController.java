package com.utkarsh.ai.chat.controller;

import com.utkarsh.ai.chat.dto.ChatDTO;
import com.utkarsh.ai.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask") //TODO: InMemoryChat history support (advisors support maybe)
    public ResponseEntity<?> ask(@RequestBody ChatDTO chatDTO) throws Throwable {
        return ResponseEntity.ok(chatService.askAI(chatDTO));
    }

}
