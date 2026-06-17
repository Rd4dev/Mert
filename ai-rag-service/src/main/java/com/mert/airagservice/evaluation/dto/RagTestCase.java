package com.mert.airagservice.evaluation.dto;

public record RagTestCase(
        String question,
        String expectedAnswer
) { }
