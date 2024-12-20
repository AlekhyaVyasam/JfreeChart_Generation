package com.Reports.ReportsProject.Services;



import com.Reports.ReportsProject.Entities.MulVal;
import com.Reports.ReportsProject.Repositories.MulValRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MulValService {

    final MulValRepository repository;

    // Save bulk data
    public void saveAllData(List<MulVal> mulVals) {
        repository.saveAll(mulVals);
    }

    // Retrieve all records
    public List<MulVal> getAllData() {
        return repository.findAll();
    }


}

