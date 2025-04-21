package com.zony.kwiktips.controller;

import com.zony.kwiktips.model.ServerPayout;
import com.zony.kwiktips.repository.ServerPayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/server-payouts")
@CrossOrigin
public class ServerPayoutController {

    @Autowired
    private ServerPayoutRepository serverPayoutRepository;

    @PostMapping
    public ServerPayout createPayout(@RequestBody ServerPayout payout){
        return serverPayoutRepository.save(payout);
    }

    @GetMapping("/report/{reportId}")
    public ResponseEntity<ServerPayout> getByReportId(@PathVariable Long reportId) {
        return serverPayoutRepository.findFirstByReportId(reportId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
