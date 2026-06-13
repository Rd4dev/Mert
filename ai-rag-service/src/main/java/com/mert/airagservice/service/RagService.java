package com.mert.airagservice.service;

import com.mert.airagservice.rag.CosineSimilarity;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RagService {
    private final PdfKnowledgeService pdfKnowledgeService;
    private final EmbeddingService embeddingService;
    private List<String> chunks;
    private final Map<String, float[]> chunkEmbeddingCache = new HashMap<>();

    public RagService(
            PdfKnowledgeService pdfKnowledgeService,
            EmbeddingService embeddingService
    ) {
        this.pdfKnowledgeService = pdfKnowledgeService;
        this.embeddingService = embeddingService;
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
}
