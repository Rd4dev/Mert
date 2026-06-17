package com.mert.airagservice.evaluation.controller;

import com.mert.airagservice.evaluation.dto.EvaluationReport;
import com.mert.airagservice.evaluation.service.EvaluationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {
    private final EvaluationService evaluationService;

    public EvaluationController(
            EvaluationService evaluationService
    ) {
        this.evaluationService = evaluationService;
    }

    @GetMapping("/run")
    public EvaluationReport run() {
        return evaluationService.generateReport();
    }
}
