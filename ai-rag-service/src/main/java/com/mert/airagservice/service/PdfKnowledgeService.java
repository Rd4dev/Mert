package com.mert.airagservice.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfKnowledgeService {

    public List<String> getPolicyChunks() throws IOException {
        ClassPathResource resource = new ClassPathResource("knowledge/merchant-refund-policy.pdf");
        PDDocument document = Loader.loadPDF(resource.getInputStream().readAllBytes());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);

        int chunkSize = 800;
        List<String> chunks = new ArrayList<>();
        for(int i=0; i<text.length(); i+=chunkSize) {
            chunks.add(text.substring(i, Math.min(text.length(), i+chunkSize)));
        }
        return chunks;
    }

//    public String getPolicyText() throws IOException {
//        ClassPathResource resource = new ClassPathResource("knowledge/merchant-refund-policy.pdf");
//        PDDocument document = Loader.loadPDF(resource.getInputStream().readAllBytes());
//        PDFTextStripper stripper = new PDFTextStripper();
//
//        return stripper.getText(document);
//    }
}