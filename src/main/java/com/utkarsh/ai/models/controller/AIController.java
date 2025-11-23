package com.utkarsh.ai.models.controller;

import com.utkarsh.ai.models.dto.AIPropDto;
import com.utkarsh.ai.models.service.AIService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ai")
public class AIController {

    private final AIService aiService;

    @PostMapping("/interact")
    public ResponseEntity<?> interact(@RequestBody AIPropDto aiPropDto) throws Throwable{
        return ResponseEntity.ok(aiService.generateAIResponse(aiPropDto));

    }


}
