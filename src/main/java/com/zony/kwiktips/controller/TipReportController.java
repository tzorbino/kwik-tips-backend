package com.zony.kwiktips.controller;

import com.zony.kwiktips.DTO.BartenderTipReportDTO;
import com.zony.kwiktips.DTO.ReportDTO;
import com.zony.kwiktips.DTO.ServerTipReportDTO;
import com.zony.kwiktips.model.TipReport;
import com.zony.kwiktips.repository.TipReportRepository;
import com.zony.kwiktips.security.JwtUtil;
import com.zony.kwiktips.service.TipReportService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/tip-reports")
public class TipReportController {

    @Autowired
    private TipReportRepository tipReportRepository;
    @Autowired
    private TipReportService tipReportService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate")
    public ResponseEntity<?> generateReport(@RequestBody ReportDTO reportDTO,
                                            @RequestHeader(value = "Authorization", required = false) String token) {
        String username = null;

        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            try {
                username = jwtUtil.validateTokenAndGetUsername(jwtToken);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token.");
            }
        }

        // Save the report and get the saved ID
        Long savedReportId = (username != null)
                ? tipReportService.saveReport(reportDTO, username)
                : tipReportService.saveAnonymousReport(reportDTO);

        // Return either a server report or bartender report based on the reportType
        if ("server".equalsIgnoreCase(reportDTO.getReportType())) {
            ServerTipReportDTO serverReport = tipReportService.getServerReport(savedReportId);
            return ResponseEntity.status(HttpStatus.CREATED).body(serverReport);
        } else {
            BartenderTipReportDTO bartenderReport = tipReportService.getBartenderReport(savedReportId);
            return ResponseEntity.status(HttpStatus.CREATED).body(bartenderReport);
        }
    }

    @GetMapping
    public List<TipReport> getAllReports() {
        return tipReportRepository.findAll();
    }

    @GetMapping("/{id}")
    public TipReport getReportById(@PathVariable Long id) {
        return tipReportRepository.findById(id).orElse(null);
    }

    @GetMapping("/bartender/{id}")
    public BartenderTipReportDTO getBartenderReport(@PathVariable Long id) {
        return tipReportService.getBartenderReport(id);
    }

    @GetMapping("/server/{reportId}")
    public ResponseEntity<ServerTipReportDTO> getServerReport(@PathVariable Long reportId) {
        ServerTipReportDTO dto = tipReportService.getServerReport(reportId);
        if(dto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<?> viewTipReport(@PathVariable Long id) {
        Optional<TipReport> reportOpt = tipReportRepository.findById(id);
        if (reportOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TipReport report = reportOpt.get();
        String type = report.getReportType(); // ✅ Make sure your TipReport entity has this field

        if ("server".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(tipReportService.getServerReport(id));
        } else {
            return ResponseEntity.ok(tipReportService.getBartenderReport(id));
        }
    }

}
