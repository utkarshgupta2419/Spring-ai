package com.utkarsh.ai.resume.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ExtractedResumeResponse(String name, String email, String summary, List<String> skills,
                                      double experience, String overallRating) {
}
