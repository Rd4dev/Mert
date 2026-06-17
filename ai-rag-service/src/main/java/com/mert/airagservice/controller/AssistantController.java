package com.mert.airagservice.controller;

import com.mert.airagservice.dto.AskRequest;
import com.mert.airagservice.service.RagService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AssistantController {
    private final RagService ragService;

    public AssistantController(RagService ragService) {
        this.ragService = ragService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AskRequest request) throws IOException {
        return ragService.ask(request.question());
    }
}
