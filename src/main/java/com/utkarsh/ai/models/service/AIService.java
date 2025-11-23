package com.utkarsh.ai.models.service;

import com.utkarsh.ai.models.dto.AIPropDto;
import com.utkarsh.ai.models.enums.AIProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class AIService {

    //    private final ChatClient chatClient;
    private final AIModelFactory aiModelFactory;
//    private final ChatClient.Builder chatClientBuilder; // Can use this if not want to use ChatModel in Constructor

    public String generateAIResponse(final AIPropDto aiPropDto) throws Throwable {
        Prompt prompt = new Prompt(aiPropDto.getInput(), aiModelFactory.createOptions(aiPropDto));
        return getAIResponse(aiPropDto, prompt);
    }

    public String generateAIResponse(final Resource resource, final AIProvider aiProvider,
                                     final String modelName, final MimeType mimeType, final String input) throws Throwable {
        if (Objects.isNull(resource)) return "Invalid Resource";
        UserMessage userMessage = generateMessage(resource, mimeType, input);
        AIPropDto aiPropDto = AIPropDto.builder()
                .aiProvider(aiProvider)
                .aiModel(modelName)
                .build();
        Prompt prompt = new Prompt(List.of(userMessage), aiModelFactory.createOptions(aiPropDto));
        return getAIResponse(aiPropDto, prompt);
    }

    private String getAIResponse(final AIPropDto aiPropDto, final Prompt prompt) {
        ChatModel chatModel = aiModelFactory.getChatModel(aiPropDto);
        ChatClient chatClient = ChatClient.create(chatModel);
        return Objects.requireNonNull(chatClient.prompt(prompt).call().chatResponse()).getResult().getOutput().getText();
    }
    public String generateAIResponseFromText(final String text, final AIPropDto aiPropDto) {
        UserMessage userMessage = new UserMessage(aiPropDto.getInput() + "\n\n" + text);

        Prompt ollamaPrompt = new Prompt(
                List.of(userMessage),
                aiModelFactory.createOptions(aiPropDto)
        );

        return getAIResponse(aiPropDto, ollamaPrompt);
    }

    private UserMessage generateMessage(final Resource imageResource, final MimeType mimeType,
                                        final String prompt) {
        return UserMessage.builder().media(new Media(mimeType, imageResource)).text(prompt).build();
    }

}
