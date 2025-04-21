package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.RoleTipPercentage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTipPercentageRepository extends JpaRepository<RoleTipPercentage,Long> {
    Optional<RoleTipPercentage> findByRole(String role);
}
