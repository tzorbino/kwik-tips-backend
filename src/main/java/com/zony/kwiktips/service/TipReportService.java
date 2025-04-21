package com.zony.kwiktips.service;

import com.zony.kwiktips.DTO.BartenderTipReportDTO;
import com.zony.kwiktips.DTO.ReportDTO;
import com.zony.kwiktips.DTO.ServerTipReportDTO;
import com.zony.kwiktips.model.*;
import com.zony.kwiktips.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TipReportService {
    @Autowired
    private TipReportRepository tipReportRepository;

    @Autowired
    private TipDistributionRepository tipDistributionRepository;

    @Autowired
    private FoodRunnerPayoutRepository foodRunnerPayoutRepository;

    @Autowired
    private ServerPayoutRepository serverPayoutRepository;

    @Autowired
    private BartenderPayoutRepository bartenderPayoutRepository;

    @Autowired
    private RoleTipPercentageRepository roleTipPercentageRepository;

    @Autowired
    private UserRepository userRepository;

    public Long saveReport(ReportDTO reportDTO, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        TipReport report = new TipReport();
        report.setUserId(user.getId());
        report.setDate(reportDTO.getDate());
        report.setShiftType(reportDTO.getShift());
        report.setTotalCashTips(reportDTO.getCashTips());
        report.setTotalCcTips(reportDTO.getCcTips());
        report.setGeneratedAt(LocalDateTime.now());

        TipReport saved = tipReportRepository.save(report);
        return saved.getId();
    }

    public Long saveAnonymousReport(ReportDTO dto) {
        // 1. Save the main report
        TipReport report = new TipReport();
        report.setDate(dto.getDate());
        report.setShiftType(dto.getShift());
        report.setTotalCashTips(dto.getCashTips());
        report.setTotalCcTips(dto.getCcTips());
        report.setReportType(dto.getReportType());
        report.setGeneratedAt(LocalDateTime.now());
        report.setBarbackCashTipPercentage(dto.getBarbackCashTipPercentage());
        report.setBarbackCcTipPercentage(dto.getBarbackCcTipPercentage());

        TipReport savedReport = tipReportRepository.save(report);
        Long reportId = savedReport.getId();

        // Time formatter for "HH:mm"
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // 2. Save bartender distributions
        if (dto.getBartenders() != null) {
            for (ReportDTO.Bartender bartender : dto.getBartenders()) {
                TipDistribution dist = new TipDistribution();
                dist.setReportId(reportId);
                dist.setName(bartender.getName());
                dist.setRole("Bartender");
                dist.setCcTips(0.0);
                dist.setCashTips(0.0);

                LocalDateTime startTime = bartender.getTimeIn();
                LocalDateTime endTime = bartender.getTimeOut();

                dist.setStartTime(startTime);
                dist.setEndTime(endTime);

                double hoursWorked = Duration.between(startTime, endTime).toMinutes() / 60.0;

                tipDistributionRepository.save(dist);
            }
        }

        // 3. Save barback distributions
        if (dto.getBarbacks() != null) {
            for (ReportDTO.Barback barback : dto.getBarbacks()) {
                TipDistribution dist = new TipDistribution();
                dist.setReportId(reportId);
                dist.setName(barback.getName());
                dist.setRole("Barback");
                dist.setCcTips(0.0);
                dist.setCashTips(0.0);

                LocalDateTime startTime = barback.getTimeIn();
                LocalDateTime endTime = barback.getTimeOut();

                double hoursWorked = Duration.between(startTime, endTime).toMinutes() / 60.0;

                dist.setStartTime(startTime);
                dist.setEndTime(endTime);
                dist.setShiftLength(barback.getShiftLength());
                dist.setHoursWorked(hoursWorked);
                dist.setShiftHours(hoursWorked + "/" + barback.getShiftLength());

                tipDistributionRepository.save(dist);
            }
        }

        // 4. Save food runner payout (optional)
        if (dto.getFoodRunners() != null && !dto.getFoodRunners().isEmpty()) {
            for (ReportDTO.FoodRunner runner : dto.getFoodRunners()) {
                FoodRunnerPayout payout = new FoodRunnerPayout();
                payout.setReportId(reportId);
                payout.setFoodSales(runner.getFoodSales());
                payout.setTipPercentage(runner.getFoodRunnerTip());

                BigDecimal tipAmount = BigDecimal.valueOf(runner.getFoodSales())
                        .multiply(BigDecimal.valueOf(runner.getFoodRunnerTip() / 100.0));

                payout.setTipAmount(tipAmount);

                foodRunnerPayoutRepository.save(payout);
            }
        }

        // 5. Save server payout (if it's a server report)
        if ("server".equalsIgnoreCase(dto.getReportType())
                && dto.getAlcoholSales() != null
                && dto.getBarTipOutPercentage() != null) {

            ServerPayout serverPayout = new ServerPayout();
            serverPayout.setReportId(reportId);
            serverPayout.setAlcoholSales(BigDecimal.valueOf(dto.getAlcoholSales()));
            serverPayout.setAlcoholTipPercentage(BigDecimal.valueOf(dto.getBarTipOutPercentage()));

            BigDecimal total = BigDecimal.valueOf(dto.getAlcoholSales())
                    .multiply(BigDecimal.valueOf(dto.getBarTipOutPercentage() / 100.0));

            serverPayout.setTotalPayout(total);

            serverPayoutRepository.save(serverPayout);

        }

        return reportId;
    }

    public BartenderTipReportDTO getBartenderReport(Long reportId) {
        TipReport report = tipReportRepository.findById(reportId).orElse(null);
        if (report == null) return null;

        List<TipDistribution> allDistributions = tipDistributionRepository.findByReportId(reportId);
        List<FoodRunnerPayout> foodRunners = foodRunnerPayoutRepository.findAllByReportId(reportId);

        double cashTipPercent = report.getBarbackCashTipPercentage() != null
                ? report.getBarbackCashTipPercentage()
                : roleTipPercentageRepository.findByRole("Barback")
                .map(RoleTipPercentage::getCashTipPercentage).orElse(0.0);

        double ccTipPercent = report.getBarbackCcTipPercentage() != null
                ? report.getBarbackCcTipPercentage()
                : roleTipPercentageRepository.findByRole("Barback")
                .map(RoleTipPercentage::getCcTipPercentage).orElse(0.0);

        BartenderTipReportDTO dto = new BartenderTipReportDTO();
        dto.setId(report.getId());
        dto.setDate(report.getDate());
        dto.setShiftType(report.getShiftType());
        dto.setTotalCashTips(report.getTotalCashTips());
        dto.setTotalCcTips(report.getTotalCcTips());
        dto.setBarbackCashTipPercentage(cashTipPercent);
        dto.setBarbackCcTipPercentage(ccTipPercent);

        double totalCash = report.getTotalCashTips();
        double totalCc = report.getTotalCcTips();

        BigDecimal foodRunnerTip = foodRunners.stream()
                .map(FoodRunnerPayout::getTipAmount)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        double totalBartenderHours = 0;
        double totalBarbackHoursWorked = 0;
        double declaredShiftLength = 0;

        List<BartenderTipReportDTO.BartenderEntry> bartenderEntries = new ArrayList<>();
        List<BartenderTipReportDTO.BarbackEntry> barbackEntries = new ArrayList<>();

        for (TipDistribution dist : allDistributions) {
            if ("Barback".equalsIgnoreCase(dist.getRole())) {
                totalBarbackHoursWorked += dist.getHoursWorked();
                if (dist.getShiftLength() > 0) {
                    declaredShiftLength = dist.getShiftLength();
                }
            } else if ("Bartender".equalsIgnoreCase(dist.getRole())) {
                double hoursWorked = 0.0;
                if (dist.getStartTime() != null && dist.getEndTime() != null) {
                    Duration duration = Duration.between(dist.getStartTime(), dist.getEndTime());
                    hoursWorked = duration.toMinutes() / 60.0;
                }

                BartenderTipReportDTO.BartenderEntry entry = new BartenderTipReportDTO.BartenderEntry();
                entry.setName(dist.getName());
                entry.setHoursWorked(hoursWorked);
                bartenderEntries.add(entry);
                totalBartenderHours += hoursWorked;
            }
        }

        // Step 1: Get full tip-out pool
        double totalBarbackCashTips = (cashTipPercent / 100.0) * totalCash;
        double totalBarbackCcTips = (ccTipPercent / 100.0) * totalCc;

        // Step 2: Prorate pool down to coverage ratio
        double coverageRatio = (declaredShiftLength > 0)
                ? Math.min(totalBarbackHoursWorked / declaredShiftLength, 1.0)
                : 0;

        double effectiveCashTipPool = totalBarbackCashTips * coverageRatio;
        double effectiveCcTipPool = totalBarbackCcTips * coverageRatio;

        double barbackCashHourly = totalBarbackHoursWorked > 0
                ? effectiveCashTipPool / totalBarbackHoursWorked
                : 0;
        double barbackCcHourly = totalBarbackHoursWorked > 0
                ? effectiveCcTipPool / totalBarbackHoursWorked
                : 0;

        for (TipDistribution dist : allDistributions) {
            if ("Barback".equalsIgnoreCase(dist.getRole())) {
                double hoursWorked = dist.getHoursWorked();
                double cashOut = hoursWorked * barbackCashHourly;
                double ccOut = hoursWorked * barbackCcHourly;

                BartenderTipReportDTO.BarbackEntry barback = new BartenderTipReportDTO.BarbackEntry();
                barback.setBarbackName(dist.getName());
                barback.setBarbackShiftHours(hoursWorked + "/" + declaredShiftLength);
                barback.setBarbackCashTips(cashOut);
                barback.setBarbackCcTips(ccOut);
                barbackEntries.add(barback);
            }
        }

        dto.setBarbackCashHourly(barbackCashHourly);
        dto.setBarbackCcHourly(barbackCcHourly);
        dto.setBarbacks(barbackEntries);

        // Subtract food runner tips ONLY from credit card tips
        BigDecimal remainingCash = BigDecimal.valueOf(totalCash)
                .subtract(BigDecimal.valueOf(effectiveCashTipPool));

        double remainingCc = totalCc
                - effectiveCcTipPool
                - foodRunnerTip.doubleValue(); // ✅ only deduct here

        double bartenderCashHourly = totalBartenderHours > 0
                ? remainingCash.divide(BigDecimal.valueOf(totalBartenderHours), 2, RoundingMode.HALF_UP).doubleValue()
                : 0;
        double bartenderCcHourly = totalBartenderHours > 0
                ? remainingCc / totalBartenderHours
                : 0;

        for (BartenderTipReportDTO.BartenderEntry entry : bartenderEntries) {
            entry.setCashTips(entry.getHoursWorked() * bartenderCashHourly);
            entry.setCcTips(entry.getHoursWorked() * bartenderCcHourly);
        }

        dto.setBartenders(bartenderEntries);
        dto.setBartenderCashHourly(bartenderCashHourly);
        dto.setBartenderCcHourly(bartenderCcHourly);
        dto.setFoodRunnerTipAmount(foodRunnerTip);

        List<BartenderTipReportDTO.FoodRunnerEntry> runnerEntries = new ArrayList<>();
        for (FoodRunnerPayout fr : foodRunners) {
            BartenderTipReportDTO.FoodRunnerEntry entry = new BartenderTipReportDTO.FoodRunnerEntry();
            entry.setFoodSales(fr.getFoodSales());
            entry.setFoodRunnerTipPercentage(fr.getTipPercentage());
            entry.setFoodRunnerTipAmount(fr.getTipAmount());
            runnerEntries.add(entry);
        }
        dto.setFoodRunners(runnerEntries);

        return dto;
    }








    public ServerTipReportDTO getServerReport(Long reportId) {
        TipReport report = tipReportRepository.findById(reportId).orElse(null);
        if (report == null) return null;

        Optional<ServerPayout> payoutOpt = serverPayoutRepository.findFirstByReportId(reportId);
        List<FoodRunnerPayout> foodRunners = foodRunnerPayoutRepository.findAllByReportId(reportId);


        ServerTipReportDTO dto = new ServerTipReportDTO();
        dto.setId(report.getId());
        dto.setDate(report.getDate());
        dto.setShiftType(report.getShiftType());
        dto.setTotalCashTips(report.getTotalCashTips());
        dto.setTotalCcTips(report.getTotalCcTips());

        payoutOpt.ifPresent(payout -> {
            dto.setAlcoholSales(payout.getAlcoholSales());
            dto.setAlcoholTipPercentage(payout.getAlcoholTipPercentage());

            // Calculate the payout instead of relying on DB
            if (payout.getAlcoholSales() != null && payout.getAlcoholTipPercentage() != null) {
                BigDecimal payoutAmount = payout.getAlcoholSales()
                        .multiply(payout.getAlcoholTipPercentage())
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

                dto.setTotalPayout(payoutAmount);
            } else {
                dto.setTotalPayout(BigDecimal.ZERO);
            }
        });

        BigDecimal totalFoodSales = BigDecimal.ZERO;
        BigDecimal totalTipAmount = BigDecimal.ZERO;
        BigDecimal tipPercentage = BigDecimal.ZERO;

        for (FoodRunnerPayout fr : foodRunners) {
            if (fr.getFoodSales() != null) {
                totalFoodSales = totalFoodSales.add(fr.getFoodSales());
            }
            if (fr.getTipAmount() != null) {
                totalTipAmount = totalTipAmount.add(fr.getTipAmount());
            }
            if (fr.getTipPercentage() != null) {
                tipPercentage = tipPercentage.add(fr.getTipPercentage());
            }
        }

        List<ServerTipReportDTO.FoodRunnerEntry> runnerEntries = new ArrayList<>();

        for (FoodRunnerPayout fr : foodRunners) {
            ServerTipReportDTO.FoodRunnerEntry entry = new ServerTipReportDTO.FoodRunnerEntry();
            entry.setFoodSales(fr.getFoodSales());
            entry.setFoodRunnerTipPercentage(fr.getTipPercentage());
            entry.setFoodRunnerTipAmount(fr.getTipAmount());
            runnerEntries.add(entry);
        }

        dto.setFoodRunners(runnerEntries);



        return dto;
    }

}
