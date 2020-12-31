package com.junho.recruitmentcollector.acceptance;

import com.junho.recruitmentcollector.domain.Company;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyAcceptanceTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    public static RequestSpecification given() {
        return RestAssured.given().log().all();
    }

    @DisplayName("회사 정보와 해당 회사의 채용 공고를 관리할 수 있어야 한다")
    @Test
    void manageCompany() {
        Company company = addCompany();

        update(company);
    }

    private Company addCompany() {
        //TODO: 회사 추가 로직
        return new Company(1L, null, null);
    }

    private void update(Company company) {
        Long companyId = company.getId();

        //@formatter:off
        given().
        when().
            get("/companies/" + companyId + "/update").
        then().
            log().all().
            statusCode(HttpStatus.NO_CONTENT.value());
        //@formatter:on
    }
}
