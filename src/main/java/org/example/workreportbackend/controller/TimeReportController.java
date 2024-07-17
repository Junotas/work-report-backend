package org.example.workreportbackend.controller;

import org.example.workreportbackend.dto.TimeReportDTO;
import org.example.workreportbackend.dto.TimeReportDateUpdateDTO;
import org.example.workreportbackend.entity.TimeReport;
import org.example.workreportbackend.service.TimeReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-reports")
@CrossOrigin
public class TimeReportController {

    private final TimeReportService timeReportService;

    public TimeReportController(TimeReportService timeReportService) {
        this.timeReportService = timeReportService;
    }

    @GetMapping
    public ResponseEntity<List<TimeReport>> getAllTimeReports() {
        List<TimeReport> allReports = timeReportService.getAllTimeReports();
        return ResponseEntity.ok(allReports);
    }

    @PostMapping
    public ResponseEntity<TimeReport> postNewTimeReport(@RequestBody TimeReportDTO timeReportDTO) {
        TimeReport timeReport = new TimeReport(timeReportDTO.getStartTime(), timeReportDTO.getEndTime());
        TimeReport savedReport = timeReportService.addNewTimeReport(timeReport, timeReportDTO.getEmployeeId());
        return ResponseEntity.ok(savedReport);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TimeReport> updateTimeReport(@PathVariable Long id, @RequestBody TimeReportDateUpdateDTO updateDTO) {
        TimeReport updatedTimeReport = timeReportService.updateTimeReport(id, updateDTO.getStartTime(), updateDTO.getEndTime());
        return ResponseEntity.ok(updatedTimeReport);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<TimeReport> approveTimeReport(@PathVariable Long id, @RequestBody TimeReportDateUpdateDTO updateDTO) {
        TimeReport updatedTimeReport = timeReportService.approveTimeReport(id, updateDTO.getIsApproved());
        return ResponseEntity.ok(updatedTimeReport);
    }
}
