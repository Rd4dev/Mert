package com.mert.airagservice.service;

import com.mert.airagservice.rag.CosineSimilarity;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RagService {
    private final PdfKnowledgeService pdfKnowledgeService;
    private final EmbeddingService embeddingService;
    private final ChatClient chatClient;

    private List<String> chunks;
    private final Map<String, float[]> chunkEmbeddingCache = new HashMap<>();

    public RagService(
            PdfKnowledgeService pdfKnowledgeService,
            EmbeddingService embeddingService,
            ChatClient.Builder chatClient
    ) {
        this.pdfKnowledgeService = pdfKnowledgeService;
        this.embeddingService = embeddingService;
        this.chatClient = chatClient.build();
    }

    @PostConstruct
    public void init() throws IOException {
        System.out.println("Loading knowledge base...");

        chunks = pdfKnowledgeService.getPolicyChunks();
        for(String chunk: chunks) {
            chunkEmbeddingCache.put(
                    chunk,
                    embeddingService.embed(chunk)
            );
        }

        System.out.println("Loaded " + chunks.size() + " chunks");
    }

    public String buildContext(String question) {
        float[] questionVector = embeddingService.embed(question);
        List<String> topChunks = chunks.stream()
            .map(chunk -> Map.entry(
                    chunk,
                    CosineSimilarity.score(
                            questionVector,
                            chunkEmbeddingCache.get(chunk)
                    )
            ))
            .sorted((a, b) ->
                    Double.compare(b.getValue(), a.getValue()))
            .limit(3)
            .map(Map.Entry::getKey)
            .toList();

        return String.join("\n", topChunks);
    }

    public String ask(String question) {
        long start = System.currentTimeMillis();
        String context = buildContext(question);

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
            .formatted(context, question);

        ChatClient.CallResponseSpec response =  chatClient.prompt(prompt).call();
        System.out.println(response.chatResponse());
        long end = System.currentTimeMillis();
        System.out.println("LATENCY ms = " + (end-start));
        return response.content();
    }
}
