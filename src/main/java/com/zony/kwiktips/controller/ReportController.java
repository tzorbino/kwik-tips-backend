//package com.zony.kwiktips.controller;
//
//import com.zony.kwiktips.DTO.ReportDTO;
//import com.zony.kwiktips.model.Report;
//import com.zony.kwiktips.security.JwtUtil;
//import com.zony.kwiktips.service.ReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/reports")
//public class ReportController {
//
//    @Autowired
//    private ReportService reportService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    // Method to generate and save reports
//    @PostMapping("/generate")
//    public ResponseEntity<?> generateReport(@RequestBody ReportDTO reportDTO, @RequestHeader(value = "Authorization", required = false) String token) {
//        String username = null;
//
//        if (token != null && token.startsWith("Bearer ")) {
//            String jwtToken = token.substring(7);
//            try {
//                username = jwtUtil.validateTokenAndGetUsername(jwtToken);
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
//            }
//        }
//
//        // Format the content as a string to save it in the report
//        String content = generateReportContent(reportDTO);
//
//        if (username != null) {
//            // Save report for logged-in user
//            reportService.saveReport(content, username);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Report generated and saved.");
//        } else {
//            // Save report for anonymous user
//            reportService.saveAnonymousReport(content);
//            return ResponseEntity.status(HttpStatus.CREATED).body("Anonymous report generated.");
//        }
//    }
//
//    // Method to view saved reports (for logged-in users)
//    @GetMapping("/view")
//    public ResponseEntity<?> viewReports() {
//        // No token validation required for viewing reports
//        List<Report> reports = reportService.getAllReports(); // Fetch all reports from the service
//        return ResponseEntity.ok(reports);
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity<?> saveReport(@RequestBody ReportDTO reportDTO) {
//        // Format the content as a string to save it in the report
//        String content = generateReportContent(reportDTO);
//
//        // Save report for anonymous user if no token is provided
//        reportService.saveAnonymousReport(content);
//        return ResponseEntity.status(HttpStatus.CREATED).body("Anonymous report saved.");
//    }
//
//    // Utility method to format the report content as a string (can be adjusted as needed)
//    private String generateReportContent(ReportDTO reportDTO) {
//        return String.format("Date: %s, Shift: %s, Cash Tips: %.2f, CC Tips: %.2f, Food Sales: %.2f, Food Runner Tip: %.2f, Barback: %s, Bartender: %s",
//                reportDTO.getDate(), reportDTO.getShift(), reportDTO.getCashTips(), reportDTO.getCcTips(),
//                reportDTO.getFoodSales(), reportDTO.getFoodRunnerTip(), reportDTO.getBarbackName(), reportDTO.getBartenderName());
//    }
//}

