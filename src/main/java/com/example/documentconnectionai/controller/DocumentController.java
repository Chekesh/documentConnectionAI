package com.example.documentconnectionai.controller;

import com.example.documentconnectionai.util.FileUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import com.example.documentconnectionai.dto.DocumentRequest;
import com.example.documentconnectionai.dto.DocumentResponse;
import com.example.documentconnectionai.service.DocumentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tika.exception.TikaException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import java.util.List;

@Slf4j
@Validated
@Tag(name = "Отпрака текста", description = "Отправлем текст к ИИ")
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @Operation(
            summary = "Заглушка GET",
            description = "Проверка работоспособности сервера на GET запрос"
    )
    @GetMapping("/check")
    public String Checked(){
        String love = "я люблю";
        String name = " прогать";
        String result = love + name;
        System.out.println(result);
        return "Дошло " + result;
    }

    @Operation(
            summary = "Отправка текста",
            description = "Отправвляем POST запрос к ИИ"
    )
    @PostMapping("/analyze")
    public ResponseEntity<DocumentResponse> analyzeDocument(
            @RequestBody @Parameter(description = "Tекст без абзацев", required = true) @Valid DocumentRequest request
    ){
        try {
            log.info("Полученный текст: {}", request.getText());
            //log.info("Received text for analysis: {}", request.getText());
            DocumentResponse response = documentService.analyzeText(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Ошибка", e);
            return ResponseEntity.status(500)
                    .body(new DocumentResponse(List.of("Ошибка: " + e.getMessage())));
        }
        /*List<String> response = documentService.analysisText(request.getText());
        return ResponseEntity.ok(new DocumentResponse(response));*/
    }


    @Operation(
            summary = "Отправка файла с текстом",
            description = "Отправляем файл с текстом для анализа ИИ"
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DocumentResponse> uploadDocument(
            @RequestParam("file") @Parameter(description = "Файл с текстом", required = true) MultipartFile file
            ){
        try {
            String extractedText = FileUtils.extractTextFromFile(file);
            log.info("Извлеченный текст из файла: {}", extractedText);

            DocumentRequest documentRequest = new DocumentRequest(extractedText);
            DocumentResponse documentResponse = documentService.analyzeText(documentRequest);
            return ResponseEntity.ok(documentResponse);
        } catch (Exception e) {
            log.error("Ошибка при обработке файла", e);
            return ResponseEntity.status(500)
                    .body(new DocumentResponse(List.of("Ошибка: " + e.getMessage())));
        }

    }

    @Operation(
            summary = "Заглушка POST",
            description = "Проверка работоспособности сервера на POST запрос"
    )
    @PostMapping("/analyz")
    public ResponseEntity<String> analyze(
            @RequestBody @Parameter(description = "Tекст без абзацев", required = true) DocumentRequest request
    ){
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(request);
            System.out.println("Request (JSON): " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("торг");
        return ResponseEntity.ok("ResponseEntity.ok(response)");
    }
}
