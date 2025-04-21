package com.zony.kwiktips.controller;

import com.zony.kwiktips.model.BartenderPayout;
import com.zony.kwiktips.repository.BartenderPayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bartender-payouts")
@CrossOrigin
public class BartenderPayoutController {

    @Autowired
    private BartenderPayoutRepository bartenderPayoutRepository;

    @PostMapping
    public BartenderPayout createPayout(@RequestBody BartenderPayout payout){
        return bartenderPayoutRepository.save(payout);
    }

    @GetMapping("/report/{reportId}")
    public BartenderPayout getByReportId(@PathVariable Long reportId){
        return bartenderPayoutRepository.findByReportId(reportId);
    }

}
