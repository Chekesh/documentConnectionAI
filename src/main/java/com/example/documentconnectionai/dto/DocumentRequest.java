package com.example.documentconnectionai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Schema(description = "Сущность текста")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequest {
    @Schema(description = "Текст", example = "Тише, Танечка, не плачь. Не утонет в речке мяч.")
    @NotEmpty(message = "Текст не может быть пустым")
    private String text;



    /*public DocumentRequest() {
    }

    public DocumentRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }*/
}
