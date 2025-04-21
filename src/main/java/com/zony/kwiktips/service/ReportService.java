//package com.zony.kwiktips.service;
//
//import com.zony.kwiktips.model.Report;
//import com.zony.kwiktips.model.User;
//import com.zony.kwiktips.repository.ReportRepository;
//import com.zony.kwiktips.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class ReportService {
//
//    @Autowired
//    private ReportRepository reportRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public void saveReport(String content, String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        Report report = new Report();
//        report.setUser(user);
//        report.setContent(content);
//        report.setGeneratedAt(new Date());
//        reportRepository.save(report);
//    }
//
//    public void saveAnonymousReport(String content) {
//        // Save the report for an anonymous user (no user linked to the report)
//        Report report = new Report();
//        report.setContent(content);
//        report.setGeneratedAt(new Date());
//        reportRepository.save(report);
//    }
//
//    public List<Report> getReportsByUser(String username) {
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        return reportRepository.findByUser(user);
//    }
//
//    public List<Report> getAllReports() {
//        // This will return all reports from the database
//        return reportRepository.findAll();
//    }
//}
