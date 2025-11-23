package com.utkarsh.ai.resume.service;

import com.utkarsh.ai.models.dto.AIPropDto;
import com.utkarsh.ai.models.enums.AIProvider;
import com.utkarsh.ai.models.service.AIService;
import com.utkarsh.ai.resume.dto.ExtractedResumeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Slf4j
public class ResumerAnalyzerService {
    private final String RESUME_ANALYZER_PROMPT = """
            Extract resume data and **STRICTLY** return a JSON object
            with **ONLY** the following six fields. Do not include any other fields like
            'workExperience' or 'education' under any circumstances. The response must be a single,
            valid JSON object.
            Use the following keys: "summary", "name", "email", "experienceYears", "skills" (as a list of strings),
            and "overallRating"
            """;

    private final AIService aiService;

    public ExtractedResumeResponse analyze(final MultipartFile resumeFile) throws Throwable {
        String resumeText;
        try (PDDocument document = Loader.loadPDF(resumeFile.getBytes())) {
            PDFTextStripper stripper = new PDFTextStripper();
            resumeText = stripper.getText(document);
        }
        AIPropDto aiPropDto = AIPropDto.builder()
                .aiModel(AIProvider.OLLAMA_MODELS.OPEN_CHAT_LATEST.getModelName())
                .aiProvider(AIProvider.OLLAMA)
                .input(RESUME_ANALYZER_PROMPT).build();
        String aiResponse = aiService.generateAIResponseFromText(resumeText, aiPropDto);
        JSONObject aiResObj = new JSONObject(aiResponse);
        return ExtractedResumeResponse.builder()
                .name(aiResObj.optString("name"))
                .email(aiResObj.optString("email"))
                .experience(Double.parseDouble(aiResObj.optString("experienceYears")))
                .skills(aiResObj.optJSONArray("skills").toList().stream().map(String::valueOf).toList())
                .summary(aiResObj.optString("summary"))
                .overallRating(aiResObj.optString("overallRating"))
                .build();
    }
}
