package com.zony.kwiktips.controller;

import com.zony.kwiktips.model.TipDistribution;
import com.zony.kwiktips.repository.TipDistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tip-distribution")
@CrossOrigin
public class TipDistributionController {

    @Autowired
    private TipDistributionRepository tipDistributionRepository;

    @PostMapping
    public TipDistribution creteEntry(@RequestBody TipDistribution entry){
        return tipDistributionRepository.save(entry);
    }

    @GetMapping("/report/{reportId}")
    public List<TipDistribution> getByReportId(@PathVariable Long reportId){
        return tipDistributionRepository.findByReportId(reportId);
    }

    @GetMapping
    public List<TipDistribution> getAll(){
        return tipDistributionRepository.findAll();
    }

}
