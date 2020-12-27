package com.junho.recruitmentcollector.crawler.staticweb;

import com.junho.recruitmentcollector.exception.ConnectionFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StaticWebPageConnectorTest {

    public static final String INVALID_URL = "???";

    private StaticWebPageConnector staticWebPageConnector;

    @BeforeEach
    void setUp() {
        this.staticWebPageConnector = new StaticWebPageConnector();
    }

    @DisplayName("접속에 실패했을때 예외를 발생시켜야 한다")
    @Test
    void connectFailTest() {
        assertThatThrownBy(() -> staticWebPageConnector.connect(INVALID_URL))
            .isInstanceOf(ConnectionFailedException.class);
    }
}