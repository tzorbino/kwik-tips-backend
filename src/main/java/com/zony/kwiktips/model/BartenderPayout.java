package com.zony.kwiktips.model;

import jakarta.persistence.*;

@Entity
@Table(name = "bartender_payouts")
public class BartenderPayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "cash_hourly")
    private Double cashHourly;

    @Column(name = "cc_hourly")
    private Double ccHourly;

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

    public Double getCashHourly() {
        return cashHourly;
    }

    public void setCashHourly(Double cashHourly) {
        this.cashHourly = cashHourly;
    }

    public Double getCcHourly() {
        return ccHourly;
    }

    public void setCcHourly(Double ccHourly) {
        this.ccHourly = ccHourly;
    }
}
