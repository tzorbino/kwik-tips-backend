package com.zony.kwiktips.controller;

import com.zony.kwiktips.model.FoodRunnerPayout;
import com.zony.kwiktips.repository.FoodRunnerPayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/food-runner-payouts")
@CrossOrigin
public class FoodRunnerPayoutController {

    @Autowired
    private FoodRunnerPayoutRepository foodRunnerPayoutRepository;

    @PostMapping
    public FoodRunnerPayout createPayout(@RequestBody FoodRunnerPayout payout){
        return foodRunnerPayoutRepository.save(payout);
    }

    @GetMapping("/report/{reportId}")
    public Optional<FoodRunnerPayout> getByReportId(@PathVariable Long reportId){
        return foodRunnerPayoutRepository.findByReportId(reportId);
    }

}
