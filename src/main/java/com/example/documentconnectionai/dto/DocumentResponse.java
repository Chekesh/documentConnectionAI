package com.example.documentconnectionai.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "Сущность абзацев")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DocumentResponse {
    @Schema(description = "Текст разделенный на абзацы"
            //, example = "[Тише, Танечка, не плачь.]\n [Не утонет в речке мяч.]"
            , example = "[\n  \"Тише, Танечка, не плачь.\",\n  \"Не утонет в речке мяч.\"\n]"
            , accessMode = Schema.AccessMode.READ_ONLY)
    private List<String> paragraph;
}
