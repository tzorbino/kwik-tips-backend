package com.zony.kwiktips.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "food_runner_payouts")
public class FoodRunnerPayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "report_Id")
    private Long reportId;

    @Column(name = "food_sales")
    private BigDecimal foodSales;

    @Column(name = "tip_percentage")
    private BigDecimal tipPercentage;

    @Column(name = "tip_amount", insertable = false, updatable = false)
    private BigDecimal tipAmount;

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

    public BigDecimal getFoodSales() {
        return foodSales;
    }

    public void setFoodSales(Double foodSales) {
        this.foodSales = BigDecimal.valueOf(foodSales);
    }

    public BigDecimal getTipPercentage() {
        return tipPercentage;
    }

    public void setTipPercentage(Double tipPercentage) {
        this.tipPercentage = BigDecimal.valueOf(tipPercentage);
    }

    public BigDecimal getTipAmount() {
        if (foodSales != null && tipPercentage != null) {
            return foodSales.multiply(tipPercentage).divide(BigDecimal.valueOf(100));
        }
        return BigDecimal.ZERO;
    }

    public void setTipAmount(BigDecimal tipAmount) {
        this.tipAmount = tipAmount;
    }


}
