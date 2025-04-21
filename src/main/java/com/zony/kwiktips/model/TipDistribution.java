package com.zony.kwiktips.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "tip_distribution")
public class TipDistribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id")
    private Long reportId;

    private String name;

    private String role;

    @Column(name = "start_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @Column(name = "shift_length")
    private Double shiftLength;

    @Column(name = "shift_hours")
    private String shiftHours;

    @Column(name = "hours_worked", insertable = false, updatable = false)
    private Double hoursWorked;

    @Column(name = "cash_tips")
    private Double cashTips;

    @Column(name = "cc_tips")
    private Double ccTips;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Double getShiftLength() {
        return shiftLength;
    }

    public void setShiftLength(Double shiftLength) {
        this.shiftLength = shiftLength;
    }

    public String getShiftHours() {
        return shiftHours;
    }

    public void setShiftHours(String shiftHours) {
        this.shiftHours = shiftHours;
    }

    public Double getHoursWorked() {
        if (startTime != null && endTime != null) {
            Duration duration = Duration.between(startTime, endTime);
            return duration.toMinutes() / 60.0;
        }
        return null;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Double getCashTips() {
        return cashTips;
    }

    public void setCashTips(Double cashTips) {
        this.cashTips = cashTips;
    }

    public Double getCcTips() {
        return ccTips;
    }

    public void setCcTips(Double ccTips) {
        this.ccTips = ccTips;
    }
}
