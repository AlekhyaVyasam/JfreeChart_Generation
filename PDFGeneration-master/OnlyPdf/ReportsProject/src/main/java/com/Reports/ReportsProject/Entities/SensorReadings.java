package com.Reports.ReportsProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sensor_readings")
public class SensorReadings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rtos_name", nullable = false, length = 100)
    private String rtosName;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "value_type", nullable = false, length = 50) // Temperature or Humidity
    private String valueType;

    @Column(name = "value", nullable = false)
    private Double value;
}

