package com.zony.kwiktips.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ServerTipReportDTO {

    private Long id;
    private LocalDate date;
    private String shiftType;
    private BigDecimal alcoholSales;
    private BigDecimal alcoholTipPercentage;
    private BigDecimal totalPayout;
    private double totalCashTips;
    private double totalCcTips;

    private List<FoodRunnerEntry> foodRunners;

    public static class FoodRunnerEntry{
        private BigDecimal foodSales;
        private BigDecimal foodRunnerTipPercentage;
        private BigDecimal foodRunnerTipAmount;

        public BigDecimal getFoodSales() {
            return foodSales;
        }

        public void setFoodSales(BigDecimal foodSales) {
            this.foodSales = foodSales;
        }

        public BigDecimal getFoodRunnerTipAmount() {
            return foodRunnerTipAmount;
        }

        public void setFoodRunnerTipAmount(BigDecimal foodRunnerTipAmount) {
            this.foodRunnerTipAmount = foodRunnerTipAmount;
        }

        public BigDecimal getFoodRunnerTipPercentage() {
            return foodRunnerTipPercentage;
        }

        public void setFoodRunnerTipPercentage(BigDecimal foodRunnerTipPercentage) {
            this.foodRunnerTipPercentage = foodRunnerTipPercentage;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalCashTips() {
        return totalCashTips;
    }

    public void setTotalCashTips(double totalCashTips) {
        this.totalCashTips = totalCashTips;
    }

    public double getTotalCcTips() {
        return totalCcTips;
    }

    public void setTotalCcTips(double totalCcTips) {
        this.totalCcTips = totalCcTips;
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

    public BigDecimal getTotalPayout() {
        return totalPayout;
    }

    public void setTotalPayout(BigDecimal totalPayout) {
        this.totalPayout = totalPayout;
    }

    public List<FoodRunnerEntry> getFoodRunners() {
        return foodRunners;
    }

    public void setFoodRunners(List<FoodRunnerEntry> foodRunners) {
        this.foodRunners = foodRunners;
    }
}
