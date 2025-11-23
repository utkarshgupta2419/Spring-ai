package com.utkarsh.ai.image.service;

import com.utkarsh.ai.image.dto.ImageDTO;
import com.utkarsh.ai.models.enums.AIProvider;
import com.utkarsh.ai.models.service.AIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.nio.file.Path;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageService {

    private static final String IMAGE_DESC_PROMPT = """
            Describe ONLY what you can actually see in this image in exactly 200 words.
                Do NOT make up or assume details that are not clearly visible.
                Do NOT invent people, objects, or scenes that you cannot see.
            
                Focus on:
                - What is actually present in the image
                - Visible colors and shapes
                - Clear, observable details only
                - The actual content, not what you think it might be
            
                Be literal and precise. If something is unclear or not visible, say so.
                Limit your response to 200 words.
            """;

    private final AIService aiService;

    public String getImageDescription(final ImageDTO imageDTO) throws Throwable {
        return aiService.generateAIResponse(getResource(imageDTO), AIProvider.OLLAMA,
                AIProvider.OLLAMA_MODELS.LLAVA_LATEST.getModelName(), MimeTypeUtils.IMAGE_JPEG, IMAGE_DESC_PROMPT);
    }

    private Resource getResource(final ImageDTO imageDTO) throws Throwable {
        if (Objects.nonNull(imageDTO.getImageFile()))
            return new ByteArrayResource(imageDTO.getImageFile().getBytes()) {
                @Override
                public String getFilename() {
                    return imageDTO.getImageFile().getOriginalFilename();
                }
            };
        if (Objects.nonNull(imageDTO.getImagePath())) {
            Path path = Path.of(imageDTO.getImagePath());
            return new FileSystemResource(path.toString());
        }
        if (Objects.nonNull(imageDTO.getImageUrl()))
            return new UrlResource(imageDTO.getImageUrl());
        return null;
    }

}
