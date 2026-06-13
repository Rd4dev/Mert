package com.mert.airagservice.controller;

import com.mert.airagservice.dto.AskRequest;
import com.mert.airagservice.service.PdfKnowledgeService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AssistantController {
    private final ChatClient chatClient;
    private final PdfKnowledgeService pdfKnowledgeService;

    String policy = """
            Returns accepted within 30 days.
            Opened products cannot be returned.
            Shipping takes 3-5 business days.
            """;

    public AssistantController(ChatClient.Builder builder, PdfKnowledgeService pdfKnowledgeService) {
        this.chatClient = builder.build();
        this.pdfKnowledgeService = pdfKnowledgeService;
    }

    @GetMapping("/policy")
    public String policy() throws IOException {
        return pdfKnowledgeService.getPolicyText();
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AskRequest request) throws IOException {
        long start = System.currentTimeMillis();
        String context = pdfKnowledgeService.getPolicyText();

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
