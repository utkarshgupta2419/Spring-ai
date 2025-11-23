package com.utkarsh.ai.chat.dto;

import com.utkarsh.ai.models.enums.AIProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ChatDTO {

    private String input;
    private AIProvider aiProvider;
    private String aiModel;

}
