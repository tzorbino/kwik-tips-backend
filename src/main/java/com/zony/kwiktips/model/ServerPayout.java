package com.zony.kwiktips.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "server_payouts")
public class ServerPayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "alcohol_sales")
    private BigDecimal alcoholSales;

    @Column(name = "alcohol_tip_percentage")
    private BigDecimal alcoholTipPercentage;

    @Column(name = "total_payout", insertable = false, updatable = false)
    private BigDecimal totalPayout;

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

    public BigDecimal getAlcoholSales() {
        return alcoholSales;
    }

    public void setAlcoholSales(BigDecimal alcoholSales) {
        this.alcoholSales = alcoholSales;
    }

    public BigDecimal getAlcoholTipPercentage() {
        return alcoholTipPercentage;
    }

    public void setAlcoholTipPercentage(BigDecimal alcoholTipPercentage) {
        this.alcoholTipPercentage = alcoholTipPercentage;
    }

    public void setTotalPayout(BigDecimal totalPayout) {
        this.totalPayout = totalPayout;
    }

    public BigDecimal getTotalPayout() {
        return totalPayout;
    }
}
