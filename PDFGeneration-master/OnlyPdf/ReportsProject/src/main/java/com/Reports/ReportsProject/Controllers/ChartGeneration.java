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
        List<SampleData> sampleDataList = sampleDataRepository.findAll();
        ByteArrayOutputStream chartOutputStream = chartGenerationService.generateGenderChart(sampleDataList);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/gender-line")
    public ResponseEntity<byte[]> generateGenderLineChart() {
        List<SampleData> sampleDataList = sampleDataRepository.findAll();
        ByteArrayOutputStream chartOutputStream = chartGenerationService.generateLineChart(sampleDataList);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

    @GetMapping("/gender-pie")
    public ResponseEntity<byte[]> generateGenderPieChart() {

        List<SampleData> sampleDataList = sampleDataRepository.findAll();
        ByteArrayOutputStream chartOutputStream = chartGenerationService.generateGenderPieChart(sampleDataList);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }

}

