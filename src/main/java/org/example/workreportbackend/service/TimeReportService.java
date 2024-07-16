package org.example.workreportbackend.service;

import org.example.workreportbackend.entity.TimeReport;
import org.example.workreportbackend.entity.Employee;
import org.example.workreportbackend.repository.TimeReportRepository;
import org.example.workreportbackend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeReportService {

    private final TimeReportRepository timeReportRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TimeReportService(TimeReportRepository timeReportRepository, EmployeeRepository employeeRepository) {
        this.timeReportRepository = timeReportRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<TimeReport> getTimeReports(String timePeriod, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        if (timePeriod.equals("past")) {
            return timeReportRepository.findAllByEmployeeAndEndTimeLessThan(employee, LocalDateTime.now());
        } else if (timePeriod.equals("future")) {
            return timeReportRepository.findAllByEmployeeAndStartTimeGreaterThan(employee, LocalDateTime.now());
        } else {
            return null;
        }
    }

    public TimeReport addNewTimeReport(TimeReport timeReport, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        timeReport.setEmployee(employee);
        return timeReportRepository.save(timeReport);
    }

    public void deleteTimeReport(Long id) {
        timeReportRepository.deleteById(id);
    }

    public TimeReport updateTimeReport(Long id, LocalDateTime startTime, LocalDateTime endTime) {
        TimeReport timeReport = timeReportRepository.findById(id).orElseThrow();
        timeReport.setStartTime(startTime);
        timeReport.setEndTime(endTime);
        return timeReportRepository.save(timeReport);
    }
}
