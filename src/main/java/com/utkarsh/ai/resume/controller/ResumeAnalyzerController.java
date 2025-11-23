package com.utkarsh.ai.resume.controller;

import com.utkarsh.ai.resume.service.ResumerAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/resume")
public class ResumeAnalyzerController {

    private final ResumerAnalyzerService resumerAnalyzerService;

    @PostMapping(value = "/analyze", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> analyzeResume(
            @RequestPart("resume") MultipartFile resumeFile
            ) throws Throwable {
        return ResponseEntity.ok(resumerAnalyzerService.analyze(resumeFile));
    }

}
