package com.example.documentconnectionai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DocumentResponse {
    private List<String> paragraph;
}
