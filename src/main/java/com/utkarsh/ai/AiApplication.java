package com.utkarsh.ai;

import com.utkarsh.ai.config.OllamaModelConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(OllamaModelConfig.class)
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

}
