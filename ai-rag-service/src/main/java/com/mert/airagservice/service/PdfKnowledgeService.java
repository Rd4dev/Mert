package com.mert.airagservice.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PdfKnowledgeService {
    public String getPolicyText() throws IOException {
        ClassPathResource resource = new ClassPathResource("knowledge/merchant-refund-policy.pdf");
        PDDocument document = Loader.loadPDF(resource.getInputStream().readAllBytes());
        PDFTextStripper stripper = new PDFTextStripper();

        return stripper.getText(document);
    }
}