package com.Reports.ReportsProject.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "mul_val")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MulVal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sensorName;

    private Double temperatureValue;

    private LocalDateTime timestamp;
}