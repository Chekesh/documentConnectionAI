package com.example.documentconnectionai.config;

import com.example.documentconnectionai.util.LoggingInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.openai.OpenAiChatClient;
//import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.ai.openai.OpenAiChatModel;


import java.util.ArrayList;
import java.util.List;
/*@Slf4j
@Configuration
public class DocumentConfig {
    @Bean
    public ChatClient chatClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://192.168.1.74:5011")
                .defaultHeader("Authorization", "Bearer ksdfnsfs")
                .filter((request, next) -> {
                    log.info("Request: " + request.method() + " " + request.url());
                    log.info("Body: " + request.body());
                    return next.exchange(request);
                })
                .build();
        ChatClient chatClient = ChatClient;
        return new ChatClient.Builder;
    }

}
/*    @Bean
    public ChatClient chatClient(OpenAiApi openAiApi) {
        // Create a RestClient with the interceptor
        RestClient restClient = RestClient.builder()
                .requestInterceptors(interceptors -> interceptors.add(loggingInterceptor()))
                .build();

        // Pass the customized RestClient to OpenAiChatClient
        return new OpenAiChatClient(openAiApi, restClient);
    }

    @Bean
    public ClientHttpRequestInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    // Optional: Define OpenAiApi if not auto-configured
    @Bean
    public OpenAiApi openAiApi() {
        return new OpenAiApi("your-openai-api-key"); // Replace with your API key
    }
}*/
/*@Configuration
public class DocumentConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors.isEmpty()) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new LoggingInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder, RestTemplate restTemplate) {
        // Set the base URL to the full desired endpoint
        OpenAiApi openAiApi = new OpenAiApi("http://192.168.1.74:5011/api/chat", "dummy-api-key");
        OpenAiChatClient chatClient = new OpenAiChatClient(openAiApi, restTemplate) {
            @Override
            public String call(String message) {
                // Override to avoid appending /v1/chat/completions
                return super.call(message);
            }
        };
        return chatClientBuilder.custom(chatClient).build();
    }
}*/

/*@Configuration
public class DocumentConfig {

    @Value("${spring.ai.ollama.base-url}")
    private String baseUrl;

    @Value("${spring.ai.ollama.chat.options.model}")
    private String modelName;

    @Bean
    public OllamaChatModel ollamaChatModel() {
        return OllamaChatModel.builder()
                .defaultOptions()
                .modelManagementOptions()
                .ollamaApi()
                .observationRegistry()
                .toolCallingManager()
                .build();
    }
}*/
