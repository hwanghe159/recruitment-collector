package com.junho.recruitmentcollector.exception;

public class ExtractFailedException extends RuntimeException {
    public ExtractFailedException(String companyName) {
        super(String.format("%s 링크 추출 중 오류가 발생했습니다.", companyName));
    }
}
