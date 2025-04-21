package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.TipDistribution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipDistributionRepository  extends JpaRepository<TipDistribution,Long> {
    List<TipDistribution> findByReportId(Long reportId);
}
