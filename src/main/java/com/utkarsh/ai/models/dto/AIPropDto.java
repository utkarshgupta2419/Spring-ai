package com.utkarsh.ai.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.utkarsh.ai.models.enums.AIProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AIPropDto {

    private String input;
    private Double temperature;
    private Integer topK;
    private Double topP;
    private Integer maxTokens;
    private AIProvider aiProvider;
    private String aiModel;

}
