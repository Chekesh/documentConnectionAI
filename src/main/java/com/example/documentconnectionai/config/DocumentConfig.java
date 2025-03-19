package com.example.documentconnectionai.config;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentConfig {

    @Value("${spring.ai.ollama.base-url}")
    private String baseUrl;

    @Value("${spring.ai.ollama.base-url}")
    private String modelPath;

    @Bean
    public OllamaChatModel ollamaChatModel(){
        //return OllamaChatModel.builder()
        //        .build();
        return new OllamaChatModel.Builder(baseUrl);
    }

}
