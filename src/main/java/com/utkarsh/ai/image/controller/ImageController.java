package com.utkarsh.ai.image.controller;

import com.utkarsh.ai.image.dto.ImageDTO;
import com.utkarsh.ai.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<?> getImageDescription(@RequestBody ImageDTO imageDTO) throws Throwable {
        return ResponseEntity.ok(imageService.getImageDescription(imageDTO));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> getImageDescForMultipartFile(
            @RequestPart("imageFile") MultipartFile file
            ) throws Throwable {
        ImageDTO imageDTO = ImageDTO.builder().imageFile(file).build();
        return ResponseEntity.ok(imageService.getImageDescription(imageDTO));
    }

}
