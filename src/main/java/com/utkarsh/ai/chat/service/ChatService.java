package com.utkarsh.ai.chat.service;

import com.utkarsh.ai.chat.dto.ChatDTO;
import com.utkarsh.ai.models.dto.AIPropDto;
import com.utkarsh.ai.models.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final AIService aiService;

    public String askAI(final ChatDTO chatDTO) throws Throwable {
        AIPropDto aiPropDto = AIPropDto.builder()
                .input(chatDTO.getInput())
                .aiProvider(chatDTO.getAiProvider())
                .aiModel(chatDTO.getAiModel())
                .build();
        return aiService.generateAIResponse(aiPropDto);
    }
}

