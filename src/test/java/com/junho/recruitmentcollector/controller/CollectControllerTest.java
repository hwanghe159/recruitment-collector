package com.junho.recruitmentcollector.controller;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CollectControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @DisplayName("채용 정보를 업데이트하고 저장할 수 있어야 한다.")
    @Test
    void collectTest() {
        //@formatter:off
        given().
        when().
            get("/collect").
        then().
            log().all().
            statusCode(HttpStatus.NO_CONTENT.value());
        //@formatter:on
    }
}