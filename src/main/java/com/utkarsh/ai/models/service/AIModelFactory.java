package com.utkarsh.ai.models.service;

import com.utkarsh.ai.config.OllamaModelConfig;
import com.utkarsh.ai.models.dto.AIPropDto;
import com.utkarsh.ai.models.enums.AIProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.model.tool.ToolCallingChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AIModelFactory {

    private final OllamaModelConfig ollamaModelConfig;
    private final OllamaChatModel ollamaChatModel;
    private final DeepSeekChatModel deepSeekChatModel;

    public ToolCallingChatOptions createOptions(final AIPropDto aiPropDto) {
        return switch (aiPropDto.getAiProvider()) {
            case OLLAMA -> OllamaOptions.builder()
                    .temperature(Objects.nonNull(aiPropDto.getTemperature())
                            ? aiPropDto.getTemperature()
                            : ollamaModelConfig.getChat().getOptions().getTemperature())
                    .topP(Objects.nonNull(aiPropDto.getTopP())
                            ? aiPropDto.getTopP()
                            : ollamaModelConfig.getChat().getOptions().getTopP())
                    .topK(Objects.nonNull(aiPropDto.getTopK())
                            ? aiPropDto.getTopK()
                            : ollamaModelConfig.getChat().getOptions().getTopK())
                    .model(Objects.nonNull(aiPropDto.getAiModel())
                            ? aiPropDto.getAiModel()
                            : Objects.nonNull(ollamaModelConfig.getChat().getOptions().getModel())
                                ? ollamaModelConfig.getChat().getOptions().getModel()
                                : AIProvider.OLLAMA_MODELS.OPEN_CHAT_LATEST.getModelName())
                    .build();

            case DEEPSEEK -> DeepSeekChatOptions.builder()
                    .temperature(aiPropDto.getTemperature())
                    .topP(aiPropDto.getTopP())
                    .maxTokens(aiPropDto.getMaxTokens())
                    .model(aiPropDto.getAiModel())
                    .build();

            default -> throw new IllegalArgumentException("Unsupported provider for chat options: %s".formatted(aiPropDto.getAiProvider()));
        };
    }


    public ChatModel getChatModel(final AIPropDto aiPropDto) {
        return switch (aiPropDto.getAiProvider()) {
            case OLLAMA -> ollamaChatModel;
            case DEEPSEEK -> deepSeekChatModel;
            default -> throw new IllegalArgumentException("Unsupported provider for chat model: %s".formatted(aiPropDto.getAiProvider()));
        };
    }
}
