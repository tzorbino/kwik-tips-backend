package com.zony.kwiktips.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ReportDTO {

    private LocalDate date;
    private String shift;
    private double cashTips;
    private double ccTips;
    private String reportType;
    private double foodSales;
    private Double alcoholSales;
    private Double barTipOutPercentage;
    private double foodRunnerTip;
    private Double barbackCashTipPercentage;
    private Double barbackCcTipPercentage;

    private List<Barback> barbacks;
    private List<Bartender> bartenders;
    private List<FoodRunner> foodRunners;

    // Getters and Setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getCashTips() {
        return cashTips;
    }

    public void setCashTips(double cashTips) {
        this.cashTips = cashTips;
    }

    public double getCcTips() {
        return ccTips;
    }

    public void setCcTips(double ccTips) {
        this.ccTips = ccTips;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public double getFoodSales() {
        return foodSales;
    }

    public void setFoodSales(double foodSales) {
        this.foodSales = foodSales;
    }

    public double getFoodRunnerTip() {
        return foodRunnerTip;
    }

    public void setFoodRunnerTip(double foodRunnerTip) {
        this.foodRunnerTip = foodRunnerTip;
    }

    public Double getAlcoholSales() {
        return alcoholSales;
    }

    public void setAlcoholSales(Double alcoholSales) {
        this.alcoholSales = alcoholSales;
    }

    public Double getBarTipOutPercentage() {
        return barTipOutPercentage;
    }

    public void setBarTipOutPercentage(Double barTipOutPercentage) {
        this.barTipOutPercentage = barTipOutPercentage;
    }

    public List<Barback> getBarbacks() {
        return barbacks;
    }

    public void setBarbacks(List<Barback> barbacks) {
        this.barbacks = barbacks;
    }

    public List<Bartender> getBartenders() {
        return bartenders;
    }

    public void setBartenders(List<Bartender> bartenders) {
        this.bartenders = bartenders;
    }

    public List<FoodRunner> getFoodRunners() {
        return foodRunners;
    }

    public void setFoodRunners(List<FoodRunner> foodRunners) {
        this.foodRunners = foodRunners;
    }

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

    // Inner classes for nested objects

    public static class Barback {
        private String name;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timeIn;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timeOut;
        private double shiftLength;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getTimeIn() {
            return timeIn;
        }

        public void setTimeIn(LocalDateTime timeIn) {
            this.timeIn = timeIn;
        }

        public LocalDateTime getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(LocalDateTime timeOut) {
            this.timeOut = timeOut;
        }

        public double getShiftLength() {
            return shiftLength;
        }

        public void setShiftLength(double shiftLength) {
            this.shiftLength = shiftLength;
        }
    }

    public static class Bartender {
        private String name;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timeIn;
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime timeOut;

        // Getters and Setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LocalDateTime getTimeIn() {
            return timeIn;
        }

        public void setTimeIn(LocalDateTime timeIn) {
            this.timeIn = timeIn;
        }

        public LocalDateTime getTimeOut() {
            return timeOut;
        }

        public void setTimeOut(LocalDateTime timeOut) {
            this.timeOut = timeOut;
        }
    }

    public static class FoodRunner {
        private double foodSales;
        private double foodRunnerTip;

        // Getters and Setters
        public double getFoodSales() {
            return foodSales;
        }

        public void setFoodSales(double foodSales) {
            this.foodSales = foodSales;
        }

        public double getFoodRunnerTip() {
            return foodRunnerTip;
        }

        public void setFoodRunnerTip(double foodRunnerTip) {
            this.foodRunnerTip = foodRunnerTip;
        }
    }
}
