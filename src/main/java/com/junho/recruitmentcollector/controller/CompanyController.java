package com.junho.recruitmentcollector.controller;

import com.junho.recruitmentcollector.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies/{companyId}/update")
    public ResponseEntity<Void> update(@PathVariable Long companyId) {
        companyService.update(companyId);
        return ResponseEntity.noContent().build();
    }
}
