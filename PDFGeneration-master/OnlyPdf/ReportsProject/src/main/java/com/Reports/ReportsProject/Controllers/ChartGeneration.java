package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Entities.SampleData;
import com.Reports.ReportsProject.Services.ChartGenerationService;
import com.Reports.ReportsProject.Repositories.SampleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/charts")
public class ChartGeneration {

    @Autowired
    private ChartGenerationService chartGenerationService;

    @Autowired
    private SampleDataRepository sampleDataRepository;

    @GetMapping("/gender-bar")
    public ResponseEntity<byte[]> generateGenderBarChart() {
        // Fetch data from the database
        List<SampleData> sampleDataList = sampleDataRepository.findAll();

        // Generate the bar chart
        ByteArrayOutputStream chartOutputStream = chartGenerationService.generateGenderChart(sampleDataList);

        // Prepare the response
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/gender-line")
    public ResponseEntity<byte[]> generateGenderLineChart() {
        // Fetch data from the database
        List<SampleData> sampleDataList = sampleDataRepository.findAll();

        // Generate the line chart
        ByteArrayOutputStream chartOutputStream = chartGenerationService.generateLineChart(sampleDataList);

        // Prepare the response
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
}

