package com.zony.kwiktips.model;

import jakarta.persistence.*;

@Entity
@Table(name = "role_tip_percentages")
public class RoleTipPercentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    @Column(name = "cash_tip_percentage")
    private Double cashTipPercentage;

    @Column(name = "cc_tip_percentage")
    private Double ccTipPercentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getCashTipPercentage() {
        return cashTipPercentage;
    }

    public void setCashTipPercentage(Double cashTipPercentage) {
        this.cashTipPercentage = cashTipPercentage;
    }

    public Double getCcTipPercentage() {
        return ccTipPercentage;
    }

    public void setCcTipPercentage(Double ccTipPercentage) {
        this.ccTipPercentage = ccTipPercentage;
    }
}
