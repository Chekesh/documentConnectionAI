package com.example.documentconnectionai.servise;

import com.example.documentconnectionai.dto.DocumentResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DocumentServise {

    private final OllamaChatModel ollamaChatModel;

    public DocumentServise(OllamaChatModel ollamaChatModel){
        this.ollamaChatModel = ollamaChatModel;
    }

    public DocumentResponse analysisText(String text){
        String instruction  = "Раздели следующий текст на смысловые части и представь их в виде списка: \n" + text;
        //Prompt prompt = new Prompt(instruction);
        String response = ollamaChatModel.call(instruction);

        return new DocumentResponse(Arrays.asList(response.split("\n")));

    }

}
