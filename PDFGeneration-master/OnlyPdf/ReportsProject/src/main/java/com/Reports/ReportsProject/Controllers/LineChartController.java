package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Entities.SensorData;
import com.Reports.ReportsProject.Services.LineChartService;
import com.Reports.ReportsProject.Repositories.SensorDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

// http://localhost:8080/api/cha/line-chart
@RestController
@RequestMapping("/api/cha")  // Base path for the controller
public class LineChartController {

    @Autowired
    private LineChartService lineChartService;

    @Autowired
    private SensorDataRepository sensorDataRepository;


    @GetMapping("/line-chart")
    public ResponseEntity<byte[]> generateLineChart() {
        // Fetch temperature sensor data from the database
        List<SensorData> sensorDataList = sensorDataRepository.findBySensorName("Temperature Sensor");

        // Generate the line chart by passing the sensor data to the service
        ByteArrayOutputStream chartOutputStream = lineChartService.generateLineChart(sensorDataList);

        // Prepare the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "image/png");

        // Return the chart image as a response
        return new ResponseEntity<>(chartOutputStream.toByteArray(), headers, HttpStatus.OK);
    }
}





