package com.example.documentconnectionai.service;

import com.example.documentconnectionai.dto.DocumentRequest;
import com.example.documentconnectionai.dto.DocumentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
//import some.package.DefaultMessage;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.james.mime4j.dom.Message;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.MessageType;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.chat.messages.ChatMessage;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionRequest;
import org.springframework.ai.openai.api.OpenAiApi.ChatCompletionMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Validated
public class DocumentService {
/*
    private final OllamaChatModel ollamaChatModel;

    public DocumentService(OllamaChatModel ollamaChatModel){
        this.ollamaChatModel = ollamaChatModel;
    }

    public DocumentResponse analyzeText(String text){

        try {


            Prompt prompt = new Prompt(
                    List.of(new ChatMessage("user", "Раздели следующий текст на смысловвые части и представь их в виде списка: \n" + text))
            );

            String instruction = "Раздели следующий текст на смысловые части и представь их в виде списка: \n" + text;
            System.out.println("Calling Ollama with instruction: " + instruction); // Отладка
            ChatResponse response = ollamaChatModel
                    .call(prompt);

            String content = response.getResult().getOutput().getText();
            //ChatResponse response = ollamaChatModel.call(prompt);



            System.out.println("Ollama response: " + response); // Отладка
            if (response == null || response.trim().isEmpty()) {
                return new DocumentResponse(List.of("No meaningful segments found"));
            }
            List<String> paragraphs = Arrays.asList(response.split("\n"));
            return new DocumentResponse(paragraphs);
        } catch (Exception e) {
            System.out.println("Error in analyzeText: " + e.getClass().getName() + " - " + e.getMessage()); // Отладка
            return new DocumentResponse(List.of("Error processing text: " + e.getMessage()));
        }

        /*String instruction  = "Раздели следующий текст на смысловые части и представь их в виде списка: \n" + text;
        //Prompt prompt = new Prompt(instruction);
        String response = ollamaChatModel.call(instruction);

        return Arrays.asList(response.split("\n"));* /

    }*/


    private final ChatClient chatClient;

    public DocumentService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public DocumentResponse analyzeText(DocumentRequest text){
        try {
            String instruction = "Раздели следующий текст на смысловые части и представь их в виде списка: \n" + text.getText();
            //String instruction = "Приввет как дела?";
            log.info("Отправвляемый запрос: " + instruction);

            log.info("Полное тело запроса: {}", chatClient.prompt().user(instruction));

            /*Message userMessage = new DefaultMessage(instruction, MessageType.USER);
            Prompt prompt = new Prompt(List.of(userMessage),
                    "model", "/home/ci-ml/mistral-small-3.1-24b-instruct-2503-Q4_K_S.gguf",
                            "stream", false,
                            "temperature", 0.7); */



            Prompt prompt = new Prompt(instruction);
            log.info("Prompt перед отправкой: " + new ObjectMapper().writeValueAsString(prompt.getInstructions()));

            ChatCompletionRequest request = new ChatCompletionRequest(
                    List.of(new ChatCompletionMessage(instruction, ChatCompletionMessage.Role.USER)),
                    "/home/ci-ml/mistral-small-3.1-24b-instruct-2503-Q4_K_S.gguf",
                    0.7
            );
            log.info("Тело запроса перед отправкой: " + new ObjectMapper().writeValueAsString(request));


            /*String content = chatClient.prompt()
                    .user(instruction)
                    .call()
                    .content();
            log.info("Сырой ответ: " + content);*/


            DocumentResponse documentResponse = chatClient.prompt()
                    .user(instruction)
                    .call()
                    .entity(DocumentResponse.class);

            log.info("Ответ: " + documentResponse);
            log.info("Ответ: " + documentResponse.getParagraph());

            return documentResponse;
            /*List<String> paragraphs = Arrays.asList(content.split("\n"));
            return new DocumentResponse(paragraphs);*/
        } catch (Exception e){
            log.info("Ошибка в анализе текста: " + e.getClass().getName() + " - " + e.getMessage());
            log.info("Сообщение ошибки: " + e.getMessage());
            e.printStackTrace();
            return new DocumentResponse(List.of("Текст ошибки: " + e.getMessage()));
        }
    }//*/

    /*private final RestClient restClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    //private static final Logger log = LoggerFactory.getLogger(DocumentService.class);

    public DocumentService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.baseUrl("http://localhost:8899").build();
    }

    public DocumentResponse analyzeText(DocumentRequest text) throws JsonProcessingException {
        String instruction = "Раздели следующий текст на смысловые части и представь их в виде списка: \n" + text.getText();
        log.info("Отправляемый запрос: {}", instruction);

        Map<String, Object> requestBody = Map.of(
                "messages", List.of(
                        Map.of(
                                "content", instruction,
                                "role", "user"
                        )
                ),
                "model", "/home/ci-ml/mistral-small-3.1-24b-instruct-2503-Q4_K_S.gguf",
                "stream", false,
                "temperature", 0.7
        );

        String jsonBody = objectMapper.writeValueAsString(requestBody);
        log.info("Тело запроса: {}", jsonBody);

        String response = restClient.post()
                .uri("/api/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer ksdfnsfs")
                .header("Transfer-Encoding", "chunked")
                .body(jsonBody)
                .retrieve()
                .body(String.class);

        log.info("Ответ: {}", response);

        /*Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        Map<String, Object> message = (Map<String, Object>) responseMap.get("choices");
        String content = (String) message.get("content");

        Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");
        Map<String, Object> choice = choices.get(0);
        Map<String, Object> message = (Map<String, Object>) choice.get("message");
        String content = (String) message.get("content");

        if (content == null) {
            throw new IllegalStateException("Response content is null");
        }
        System.out.println(content);
        List<String> paragraphs = Arrays.asList(content.split("\n"));
        return new DocumentResponse(paragraphs);
    }*/
}
