package org.example.workreportbackend.dto;

import java.time.LocalDateTime;

public class TimeReportDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long employeeId;

    // Getters and Setters

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
