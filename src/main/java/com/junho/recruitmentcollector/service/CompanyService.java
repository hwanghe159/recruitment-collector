package com.junho.recruitmentcollector.service;

import com.junho.recruitmentcollector.domain.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void update(Long companyId) {
        //companyId에 해당하는 회사의 모든 모집 공고 업데이트
    }
}
