package com.mert.airagservice.controller;

import com.mert.airagservice.dto.AskRequest;
import com.mert.airagservice.service.RagService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AssistantController {
    private final ChatClient chatClient;
    private final RagService ragService;

    public AssistantController(ChatClient.Builder builder, RagService ragService) {
        this.chatClient = builder.build();
        this.ragService = ragService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AskRequest request) throws IOException {
        long start = System.currentTimeMillis();
        String context = ragService.buildContext(request.question());

        String prompt = """
            You are a merchant support assistant.
            Answer ONLY using the provided context.
            If the answer is not present in the context, respond exactly with:
            "I don't have enough information to answer that."
            
            Context:
            %s
            
            Question:
            %s
            """
            .formatted(context, request.question());

        ChatClient.CallResponseSpec response =  chatClient.prompt(prompt)
                .call();
        System.out.println(response.chatResponse());
        long end = System.currentTimeMillis();
        System.out.println("LATENCY ms = " + (end-start));
        return response.content();
    }
}
