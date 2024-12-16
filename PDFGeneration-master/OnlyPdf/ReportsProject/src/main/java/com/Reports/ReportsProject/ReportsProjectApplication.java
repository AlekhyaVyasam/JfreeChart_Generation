package com.Reports.ReportsProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

public class ReportsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportsProjectApplication.class, args);
		System.out.println("Executed Successfully");
	}

}
