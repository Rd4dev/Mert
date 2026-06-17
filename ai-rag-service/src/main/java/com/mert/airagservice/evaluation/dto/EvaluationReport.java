package com.mert.airagservice.evaluation.dto;

import java.util.List;

public record EvaluationReport(
        int totalTests,
        double averageScore,
        long passed,
        long failed,
        List<EvaluationResult> results
) {
}
