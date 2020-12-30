package com.junho.recruitmentcollector.exception;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(Long companyId) {
        super(String.format("id %d에 해당하는 회사가 존재하지 않습니다.", companyId));
    }
}
