package com.mert.airagservice.controller;

import com.mert.airagservice.dto.AskRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssistantController {
    private final ChatClient chatClient;

    public AssistantController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AskRequest request) {
        return chatClient.prompt(request.question())
                .call()
                .content();
    }
}
