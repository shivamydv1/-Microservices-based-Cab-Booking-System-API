package com.ai_assistent.AiAssistantService;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
    private final AiService aiService;
    private final OllamaChatModel ollamaChatModel;
    
    @GetMapping("/query")
    public String reply(@RequestParam String userId, @RequestParam String userPrompt) {
        return aiService.generateSmartReply(userId, userPrompt);
    }

    @GetMapping("/prompt")
    public String promptResponse(@RequestParam("prompt") String prompt ) {
        String response = ollamaChatModel.call(prompt);
        return response;
    }
}
