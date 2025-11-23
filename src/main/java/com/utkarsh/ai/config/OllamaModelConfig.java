package com.utkarsh.ai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.ai.ollama")
@Getter
@Setter
public class OllamaModelConfig {

    private String baseUrl;
    private Chat chat;

    @Setter
    @Getter
    public static class Chat {
        private Options options;

        @Setter
        @Getter
        public static class Options {
            private String model;
            private double temperature;
            private double topP;
            private int topK;
        }
    }

}
