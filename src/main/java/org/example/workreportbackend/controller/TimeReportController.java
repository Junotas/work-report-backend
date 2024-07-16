package org.example.workreportbackend.controller;

import org.example.workreportbackend.dto.TimeReportDTO;
import org.example.workreportbackend.service.TimeReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/time-reports")
public class TimeReportController {

    @Autowired
    private TimeReportService timeReportService;

    @PostMapping
    public ResponseEntity<TimeReportDTO> addTimeReport(@RequestBody TimeReportDTO timeReportDTO) {
        TimeReportDTO savedTimeReport = timeReportService.saveTimeReport(timeReportDTO);
        return new ResponseEntity<>(savedTimeReport, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<TimeReportDTO>> getTimeReportsByEmployee(@PathVariable Long employeeId) {
        List<TimeReportDTO> timeReports = timeReportService.getTimeReportsByEmployeeId(employeeId);
        return new ResponseEntity<>(timeReports, HttpStatus.OK);
    }

    @GetMapping("/approval-status/{isApproved}")
    public ResponseEntity<List<TimeReportDTO>> getTimeReportsByApprovalStatus(@PathVariable Boolean isApproved) {
        List<TimeReportDTO> timeReports = timeReportService.getTimeReportsByApprovalStatus(isApproved);
        return new ResponseEntity<>(timeReports, HttpStatus.OK);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<TimeReportDTO> approveTimeReport(@PathVariable Long id) {
        TimeReportDTO approvedTimeReport = timeReportService.approveTimeReport(id);
        return new ResponseEntity<>(approvedTimeReport, HttpStatus.OK);
    }
}
