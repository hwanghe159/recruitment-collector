package com.junho.recruitmentcollector.exception;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException(String url) {
        super(String.format("%s 접속에 실패했습니다.", url));
    }
}
