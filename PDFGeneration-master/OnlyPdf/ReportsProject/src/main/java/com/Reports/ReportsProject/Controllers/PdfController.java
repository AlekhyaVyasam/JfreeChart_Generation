package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Services.PDFGenerationService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class PdfController {
    @Autowired
    private PDFGenerationService pdfGenerationService;
    @GetMapping("/generatepdf")
    public ResponseEntity<byte[]> generatePDF() {
        try {
            byte[] pdfBytes = pdfGenerationService.generatePDF().toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=SampleData.pdf");
            headers.add("Content-Type", "application/pdf");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

}
