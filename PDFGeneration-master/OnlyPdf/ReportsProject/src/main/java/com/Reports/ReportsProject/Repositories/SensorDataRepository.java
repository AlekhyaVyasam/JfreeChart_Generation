package com.Reports.ReportsProject.Repositories;

import com.Reports.ReportsProject.Entities.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    List<SensorData> findBySensorName(String sensorName);
}