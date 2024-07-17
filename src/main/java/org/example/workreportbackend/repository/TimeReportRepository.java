package org.example.workreportbackend.repository;

import org.example.workreportbackend.entity.TimeReport;
import org.example.workreportbackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {
    List<TimeReport> findAllByEmployeeAndStartTimeGreaterThan(Employee employee, LocalDateTime startTime);
    List<TimeReport> findAllByEmployeeAndEndTimeLessThan(Employee employee, LocalDateTime endTime);
}
