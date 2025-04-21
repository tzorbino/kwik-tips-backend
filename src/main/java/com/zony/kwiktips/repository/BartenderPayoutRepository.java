package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.BartenderPayout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BartenderPayoutRepository extends JpaRepository<BartenderPayout, Long> {
    BartenderPayout findByReportId(Long reportId);
}
