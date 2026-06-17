package com.mert.airagservice.evaluation.dto;

public record EvaluationResult(
        String question,
        String expectedAnswer,
        String actualAnswer,
        int correctness
) { }
