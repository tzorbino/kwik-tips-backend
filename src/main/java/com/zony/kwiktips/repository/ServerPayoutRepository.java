package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.ServerPayout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerPayoutRepository extends JpaRepository<ServerPayout, Long> {
    Optional<ServerPayout> findFirstByReportId(Long reportId);
}
