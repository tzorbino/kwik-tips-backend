package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.FoodRunnerPayout;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRunnerPayoutRepository extends JpaRepository<FoodRunnerPayout, Long> {
    Optional<FoodRunnerPayout> findByReportId(Long reportId);

    List<FoodRunnerPayout> findAllByReportId(Long reportId);
}
