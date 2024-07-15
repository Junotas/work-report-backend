package org.example.workreportbackend.dto;

import java.time.LocalDateTime;

public class TimeReportDTO {
    private Long id;
    private Long employeeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isApproved;

    // getters and setters
}
