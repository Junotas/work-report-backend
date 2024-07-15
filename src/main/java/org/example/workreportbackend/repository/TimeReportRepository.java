package org.example.workreportbackend.repository;

import org.example.workreportbackend.entity.TimeReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {
    List<TimeReport> findByEmployeeId(Long employeeId);
    List<TimeReport> findByIsApproved(Boolean isApproved);
}
