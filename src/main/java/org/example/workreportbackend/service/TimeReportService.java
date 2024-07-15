package org.example.workreportbackend.service;

import org.example.workreportbackend.dto.TimeReportDTO;
import org.example.workreportbackend.entity.Employee;
import org.example.workreportbackend.entity.TimeReport;
import org.example.workreportbackend.repository.EmployeeRepository;
import org.example.workreportbackend.repository.TimeReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeReportService {

    private final TimeReportRepository timeReportRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TimeReportService(TimeReportRepository timeReportRepository, EmployeeRepository employeeRepository) {
        this.timeReportRepository = timeReportRepository;
        this.employeeRepository = employeeRepository;
    }

    public TimeReportDTO saveTimeReport(TimeReportDTO timeReportDTO) {
        TimeReport timeReport = new TimeReport();
        Optional<Employee> employee = employeeRepository.findById(timeReportDTO.getEmployeeId());
        if (employee.isEmpty()) {
            throw new RuntimeException("Employee not found");
        }
        timeReport.setEmployee(employee.get());
        timeReport.setStartTime(timeReportDTO.getStartTime());
        timeReport.setEndTime(timeReportDTO.getEndTime());
        timeReport.setIsApproved(timeReportDTO.getIsApproved());
        timeReportRepository.save(timeReport);
        return toDTO(timeReport);
    }

    public List<TimeReportDTO> getTimeReportsByEmployeeId(Long employeeId) {
        return timeReportRepository.findByEmployeeId(employeeId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TimeReportDTO> getTimeReportsByApprovalStatus(Boolean isApproved) {
        return timeReportRepository.findByIsApproved(isApproved).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TimeReportDTO approveTimeReport(Long id) {
        Optional<TimeReport> timeReport = timeReportRepository.findById(id);
        if (timeReport.isEmpty()) {
            throw new RuntimeException("Time report not found");
        }
        timeReport.get().setIsApproved(true);
        timeReportRepository.save(timeReport.get());
        return toDTO(timeReport.get());
    }

    private TimeReportDTO toDTO(TimeReport timeReport) {
        TimeReportDTO dto = new TimeReportDTO();
        dto.setId(timeReport.getId());
        dto.setEmployeeId(timeReport.getEmployee().getId());
        dto.setStartTime(timeReport.getStartTime());
        dto.setEndTime(timeReport.getEndTime());
        dto.setIsApproved(timeReport.getIsApproved());
        return dto;
    }
}
