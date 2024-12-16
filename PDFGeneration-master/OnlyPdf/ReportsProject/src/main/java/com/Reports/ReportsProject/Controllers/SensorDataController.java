package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Entities.SensorData;
import com.Reports.ReportsProject.Services.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping("/add")
    public SensorData addSensorData(@RequestBody SensorData sensorData) {
        return sensorDataService.saveSensorData(sensorData.getSensorName(), sensorData.getTimestamp(), sensorData.getValue());
    }



    // Get all sensor data
    @GetMapping("/all")
    public List<SensorData> getAllSensorData() {
        return sensorDataService.getAllSensorData();
    }




}
