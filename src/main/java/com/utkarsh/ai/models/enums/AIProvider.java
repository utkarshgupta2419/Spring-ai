package com.utkarsh.ai.models.enums;

import lombok.Getter;


public enum AIProvider {
    OLLAMA,
    DEEPSEEK;

    public enum OLLAMA_MODELS {
        OPEN_CHAT_LATEST("openchat:latest"),
        LLAVA_LATEST("llava:latest");

        @Getter
        private final String modelName;

        OLLAMA_MODELS(String modelName) {
            this.modelName = modelName;
        }
    }

    public enum DEEPSEEK_MODELS {
        R1T2_CHIMERA("tngtech/deepseek-r1t2-chimera:free");

        @Getter
        private final String modelName;

        DEEPSEEK_MODELS(String modelName) {
            this.modelName = modelName;
        }
    }
}
