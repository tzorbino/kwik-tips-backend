package com.zony.kwiktips.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tip_reports")
public class TipReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    private LocalDate date;

    @Column(name = "shift_type")
    private String shiftType;

    @Column(name = "total_cash_tips")
    private Double totalCashTips;

    @Column(name = "total_cc_tips")
    private Double totalCcTips;

    @Column
    private Double foodSales;

    @Column
    private Double foodRunnerTipPercentage;

    @Column
    private String content;

    @Column
    private LocalDateTime generatedAt;
    @Column(name = "report_type")
    private String reportType;

    @Column(name = "barback_cash_tip_percentage")
    private Double barbackCashTipPercentage;

    @Column(name = "barback_cc_tip_percentage")
    private Double barbackCcTipPercentage;


    public Double getBarbackCashTipPercentage() {
        return barbackCashTipPercentage;
    }

    public void setBarbackCashTipPercentage(Double barbackCashTipPercentage) {
        this.barbackCashTipPercentage = barbackCashTipPercentage;
    }

    public Double getBarbackCcTipPercentage() {
        return barbackCcTipPercentage;
    }

    public void setBarbackCcTipPercentage(Double barbackCcTipPercentage) {
        this.barbackCcTipPercentage = barbackCcTipPercentage;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShiftType() {
        return shiftType;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public Double getTotalCashTips() {
        return totalCashTips;
    }

    public void setTotalCashTips(Double totalCashTips) {
        this.totalCashTips = totalCashTips;
    }

    public Double getTotalCcTips() {
        return totalCcTips;
    }

    public void setTotalCcTips(Double totalCcTips) {
        this.totalCcTips = totalCcTips;
    }

    public Double getFoodSales() {
        return foodSales;
    }

    public void setFoodSales(Double foodSales) {
        this.foodSales = foodSales;
    }

    public Double getFoodRunnerTipPercentage() {
        return foodRunnerTipPercentage;
    }

    public void setFoodRunnerTipPercentage(Double foodRunnerTipPercentage) {
        this.foodRunnerTipPercentage = foodRunnerTipPercentage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
}
