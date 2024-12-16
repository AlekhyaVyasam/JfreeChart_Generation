package com.Reports.ReportsProject.Controllers;

import com.Reports.ReportsProject.Entities.SampleData;
import com.Reports.ReportsProject.Services.SampleDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SampleDataController {

    @Autowired
    private SampleDataService sampleDataService;

    @PostMapping("/insert")
    public SampleData addSampleData(@RequestBody SampleData sampleData){
        return sampleDataService.saveSampleData(sampleData.getName(), sampleData.getEmail(), sampleData.getGender());
    }

    @GetMapping("/retrieve")
    public List<SampleData> getAllSampleData() {
        return sampleDataService.getAllSampleData();
    }


}
