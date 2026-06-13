package com.mert.airagservice.service;

import com.mert.airagservice.rag.CosineSimilarity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RagService {
    private final PdfKnowledgeService pdfKnowledgeService;
    private final EmbeddingService embeddingService;
    private final Map<String, float[]> chunkEmbeddingCache = new HashMap<>();

    public RagService(
            PdfKnowledgeService pdfKnowledgeService,
            EmbeddingService embeddingService
    ) {
        this.pdfKnowledgeService = pdfKnowledgeService;
        this.embeddingService = embeddingService;
    }

    public String buildContext(String question) throws IOException {
        List<String> chunks = pdfKnowledgeService.getPolicyChunks();
        float[] questionVector = embeddingService.embed(question);
        List<String> topChunks = chunks.stream()
            .sorted((a, b) -> {

                float[] aVec = chunkEmbeddingCache.computeIfAbsent(a, embeddingService::embed);
                float[] bVec = chunkEmbeddingCache.computeIfAbsent(b, embeddingService::embed);

                double scoreA = CosineSimilarity.score(questionVector, aVec);
                double scoreB = CosineSimilarity.score(questionVector, bVec);

                return Double.compare(scoreB, scoreA);
            })
            .limit(3)
            .toList();

        return String.join("\n", topChunks);
    }
}
