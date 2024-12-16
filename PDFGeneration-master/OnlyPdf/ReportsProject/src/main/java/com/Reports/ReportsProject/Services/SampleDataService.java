package com.Reports.ReportsProject.Services;

import com.Reports.ReportsProject.Entities.SampleData;
import com.Reports.ReportsProject.Repositories.SampleDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SampleDataService {

    @Autowired
    private SampleDataRepository sampleDataRepository;

    public SampleData saveSampleData(String name, String email, String gender) {
        SampleData sampleData = new SampleData();
        sampleData.setName(name);
        sampleData.setEmail(email);
        sampleData.setGender(gender);
        return sampleDataRepository.save(sampleData);
    }

    public List<SampleData> getAllSampleData() {
        return sampleDataRepository.findAll();
    }


}
