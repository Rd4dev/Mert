package com.mert.airagservice.evaluation.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class RagJudgeService {
    private final ChatClient chatClient;

    public RagJudgeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public int score(
            String question,
            String expectedAnswer,
            String actualAnswer
    ) {
        String prompt = """
                Question:
                %s
                
                Expected Answer
                %s
                
                Actual Answer
                %s
                
                Score the actual answer from 1-5.
                
                Return ONLY a number.
                
                5 = fully correct
                4 = mostly correct
                3 = partially correct
                2 = mostly incorrect
                1 = completely incorrect
                """
                .formatted(
                        question,
                        expectedAnswer,
                        actualAnswer
                );

        String response = chatClient.prompt(prompt)
                            .call()
                            .content();

        return Integer.parseInt(response.trim());
    }
}
