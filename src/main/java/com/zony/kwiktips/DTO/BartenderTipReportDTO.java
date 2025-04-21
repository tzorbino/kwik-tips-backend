package com.zony.kwiktips.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BartenderTipReportDTO {

    private Long id;
    private LocalDate date;
    private String shiftType;
    private double totalCashTips;
    private double totalCcTips;

    private BigDecimal foodRunnerTipAmount;

    private double bartenderCashHourly;
    private double bartenderCcHourly;

    private double barbackCashHourly;
    private double barbackCcHourly;

    private Double barbackCashTipPercentage;
    private Double barbackCcTipPercentage;

    private List<BartenderEntry> bartenders;

    private List<FoodRunnerEntry> foodRunners;

    public List<FoodRunnerEntry> getFoodRunners() {
        return foodRunners;
    }

    public void setFoodRunners(List<FoodRunnerEntry> foodRunners) {
        this.foodRunners = foodRunners;
    }

    private List<BarbackEntry> barbacks;

    public List<BarbackEntry> getBarbacks() {
        return barbacks;
    }

    public void setBarbacks(List<BarbackEntry> barbacks) {
        this.barbacks = barbacks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBarbackCcTipPercentage() {
        return barbackCcTipPercentage;
    }

    public void setBarbackCcTipPercentage(Double barbackCcTipPercentage) {
        this.barbackCcTipPercentage = barbackCcTipPercentage;
    }

    public Double getBarbackCashTipPercentage() {
        return barbackCashTipPercentage;
    }

    public void setBarbackCashTipPercentage(Double barbackCashTipPercentage) {
        this.barbackCashTipPercentage = barbackCashTipPercentage;
    }

    public static class BartenderEntry {
        private String name;
        private double hoursWorked;
        private double cashTips;
        private double ccTips;


        public String getName() {
            return name;
        }

        public double getHoursWorked() {
            return hoursWorked;
        }

        public double getCashTips() {
            return cashTips;
        }

        public double getCcTips() {
            return ccTips;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHoursWorked(double hoursWorked) {
            this.hoursWorked = hoursWorked;
        }

        public void setCashTips(double cashTips) {
            this.cashTips = cashTips;
        }

        public void setCcTips(double ccTips) {
            this.ccTips = ccTips;
        }
    }

    public static class BarbackEntry {
        private String barbackName;
        private String barbackShiftHours;
        private double barbackCashTips;
        private double barbackCcTips;

        public String getBarbackName() {
            return barbackName;
        }

        public void setBarbackName(String barbackName) {
            this.barbackName = barbackName;
        }

        public String getBarbackShiftHours() {
            return barbackShiftHours;
        }

        public void setBarbackShiftHours(String barbackShiftHours) {
            this.barbackShiftHours = barbackShiftHours;
        }

        public double getBarbackCashTips() {
            return barbackCashTips;
        }

        public void setBarbackCashTips(double barbackCashTips) {
            this.barbackCashTips = barbackCashTips;
        }

        public double getBarbackCcTips() {
            return barbackCcTips;
        }

        public void setBarbackCcTips(double barbackCcTips) {
            this.barbackCcTips = barbackCcTips;
        }
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setShiftType(String shiftType) {
        this.shiftType = shiftType;
    }

    public void setTotalCashTips(double totalCashTips) {
        this.totalCashTips = totalCashTips;
    }

    public void setTotalCcTips(double totalCcTips) {
        this.totalCcTips = totalCcTips;
    }

    public void setFoodRunnerTipAmount(BigDecimal foodRunnerTipAmount) {
        this.foodRunnerTipAmount = foodRunnerTipAmount;
    }

    public void setBartenderCashHourly(double bartenderCashHourly) {
        this.bartenderCashHourly = bartenderCashHourly;
    }

    public void setBartenderCcHourly(double bartenderCcHourly) {
        this.bartenderCcHourly = bartenderCcHourly;
    }

    public double getBarbackCashHourly() {
        return barbackCashHourly;
    }

    public void setBarbackCashHourly(double barbackCashHourly) {
        this.barbackCashHourly = barbackCashHourly;
    }

    public double getBarbackCcHourly() {
        return barbackCcHourly;
    }

    public void setBarbackCcHourly(double barbackCcHourly) {
        this.barbackCcHourly = barbackCcHourly;
    }

    public void setBartenders(List<BartenderEntry> bartenders) {
        this.bartenders = bartenders;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getShiftType() {
        return shiftType;
    }

    public double getTotalCashTips() {
        return totalCashTips;
    }

    public double getTotalCcTips() {
        return totalCcTips;
    }

    public BigDecimal getFoodRunnerTipAmount() {
        return foodRunnerTipAmount;
    }


    public double getBartenderCashHourly() {
        return bartenderCashHourly;
    }

    public double getBartenderCcHourly() {
        return bartenderCcHourly;
    }

    public List<BartenderEntry> getBartenders() {
        return bartenders;
    }

    public static class FoodRunnerEntry {
        private BigDecimal foodSales;
        private BigDecimal foodRunnerTipPercentage;
        private BigDecimal foodRunnerTipAmount;

        public BigDecimal getFoodSales() {
            return foodSales;
        }

        public void setFoodSales(BigDecimal foodSales) {
            this.foodSales = foodSales;
        }

        public BigDecimal getFoodRunnerTipPercentage() {
            return foodRunnerTipPercentage;
        }

        public void setFoodRunnerTipPercentage(BigDecimal foodRunnerTipPercentage) {
            this.foodRunnerTipPercentage = foodRunnerTipPercentage;
        }

        public BigDecimal getFoodRunnerTipAmount() {
            return foodRunnerTipAmount;
        }

        public void setFoodRunnerTipAmount(BigDecimal foodRunnerTipAmount) {
            this.foodRunnerTipAmount = foodRunnerTipAmount;
        }
    }

}
