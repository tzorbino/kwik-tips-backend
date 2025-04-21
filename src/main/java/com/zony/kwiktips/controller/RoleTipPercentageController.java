package com.zony.kwiktips.controller;

import com.zony.kwiktips.model.RoleTipPercentage;
import com.zony.kwiktips.repository.RoleTipPercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role-tip-percentages")
@CrossOrigin
public class RoleTipPercentageController {

    @Autowired
    private RoleTipPercentageRepository repository;

    @PostMapping
    public RoleTipPercentage create(@RequestBody RoleTipPercentage tipPercentage) {
        return repository.save(tipPercentage);
    }

    @GetMapping
    public List<RoleTipPercentage> getAll() {
        return repository.findAll();
    }

    @GetMapping("/by-role/{role}")
    public Optional<RoleTipPercentage> getByRole(@PathVariable String role) {
        return repository.findByRole(role);
    }

    @GetMapping("/{id}")
    public RoleTipPercentage update(@PathVariable Long id, @RequestBody RoleTipPercentage updated) {
        updated.setId(id);
        return repository.save(updated);
    }

    @PutMapping("/{role}")
    public RoleTipPercentage updateByRole(@PathVariable String role, @RequestBody RoleTipPercentage updated) {
        RoleTipPercentage existing = repository.findByRole(role).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        existing.setCashTipPercentage(updated.getCashTipPercentage());
        existing.setCcTipPercentage(updated.getCcTipPercentage());
        return repository.save(existing);
    }

}
