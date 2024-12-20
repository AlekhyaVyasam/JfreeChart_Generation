package com.Reports.ReportsProject.Controllers;


import com.Reports.ReportsProject.Entities.MulVal;
import com.Reports.ReportsProject.Services.MulValService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mulval")
@RequiredArgsConstructor
public class MulValController {

    private final MulValService mulValService;

    // Bulk Insert API
    @PostMapping("/bulk-insert")
    public ResponseEntity<String> insertBulkData(@RequestBody List<MulVal> mulVals) {
        mulValService.saveAllData(mulVals);
        return ResponseEntity.ok("Bulk data inserted successfully!");
    }

    // Retrieve All Data API
    @GetMapping("/all")
    public ResponseEntity<List<MulVal>> getAllData() {
        return ResponseEntity.ok(mulValService.getAllData());
    }
}

