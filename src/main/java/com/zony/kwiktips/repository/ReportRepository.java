package com.zony.kwiktips.repository;

import com.zony.kwiktips.model.Report;
import com.zony.kwiktips.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByUser(User user);
}
