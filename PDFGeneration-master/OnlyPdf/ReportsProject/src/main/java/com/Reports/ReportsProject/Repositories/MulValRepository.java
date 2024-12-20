package com.Reports.ReportsProject.Repositories;



import com.Reports.ReportsProject.Entities.MulVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MulValRepository extends JpaRepository<MulVal, Long> {
}

