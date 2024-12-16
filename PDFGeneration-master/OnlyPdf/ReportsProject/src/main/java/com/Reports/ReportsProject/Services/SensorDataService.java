package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.SensorData;
import com.Reports.ReportsProject.Repositories.SensorDataRepository;
import org.jfree.chart.ChartPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class SensorDataService {


    @Autowired
    private SensorDataRepository sensorDataRepository;

    public SensorData saveSensorData(String sensorName,  LocalDateTime timestamp,Double value ) {
        SensorData sensorData = new SensorData();
        sensorData.setSensorName(sensorName);
        sensorData.setTimestamp(timestamp);
        sensorData.setValue(value);

        return sensorDataRepository.save(sensorData);
    }

    // Retrieve all sensor data
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }



}
