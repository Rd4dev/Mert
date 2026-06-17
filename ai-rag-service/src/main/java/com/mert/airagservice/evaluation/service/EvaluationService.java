package com.mert.airagservice.evaluation.service;

import com.mert.airagservice.evaluation.dataset.MerchantPolicyDataset;
import com.mert.airagservice.evaluation.dto.EvaluationReport;
import com.mert.airagservice.evaluation.dto.EvaluationResult;
import com.mert.airagservice.service.RagService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {
    private final MerchantPolicyDataset dataset;
    private final RagService ragService;
    private final RagJudgeService ragJudgeService;

    public EvaluationService(
            MerchantPolicyDataset dataset,
            RagService ragService,
            RagJudgeService ragJudgeService
    ) {
        this.dataset = dataset;
        this.ragService = ragService;
        this.ragJudgeService = ragJudgeService;
    }

    public List<EvaluationResult> runEvaluation() {
        return dataset.getTestCases().stream().map(testCase -> {
            String actualAnswer = ragService.ask(testCase.question());
            int score = ragJudgeService.score(
                    testCase.question(),
                    testCase.expectedAnswer(),
                    actualAnswer
            );

            return new EvaluationResult(
                    testCase.question(),
                    testCase.expectedAnswer(),
                    actualAnswer,
                    score
            );
        }).toList();
    }

    public EvaluationReport generateReport() {
        List<EvaluationResult> results = runEvaluation();

        double averageScore = results.stream()
                .mapToInt(EvaluationResult::correctness)
                .average()
                .orElse(0);

        long passed = results.stream()
                .filter(r -> r.correctness() >= 4)
                .count();

        long failed = results.size() - passed;

        return new EvaluationReport(
                results.size(),
                averageScore,
                passed,
                failed,
                results
        );
    }
}
