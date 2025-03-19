package com.example.documentconnectionai.controller;

import com.example.documentconnectionai.dto.DocumentRequest;
import com.example.documentconnectionai.dto.DocumentResponse;
import com.example.documentconnectionai.servise.DocumentServise;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentServise documentServise;

    public DocumentController(DocumentServise documentServise){
        this.documentServise = documentServise;
    }

    public ResponseEntity<DocumentResponse> alizeDocument(@RequestBody DocumentRequest request){
        DocumentResponse documentResponse = documentServise.analysisText(request.getText());
        return ResponseEntity.ok(documentResponse);
    }
}
