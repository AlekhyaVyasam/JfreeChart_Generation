package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Services.MulValChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MulValChartController {

    @Autowired
    private MulValChartService mulValChartService;

    // Endpoint to generate the chart
    @GetMapping("/generateLineChart")
    public ResponseEntity<byte[]> generateLineChart() {
        try {
            // Generate the chart byte array
            byte[] imageByteArray = mulValChartService.generateLineChart();

            // Set headers to indicate content type is PNG image
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            // Remove Content-Disposition or set it to inline without filename
            headers.set("Content-Disposition", "inline");

            // Return the image as a response entity
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(imageByteArray);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
