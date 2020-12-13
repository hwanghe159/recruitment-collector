package com.junho.recruitmentcollector.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecruitCrawlerTest {

    private RecruitCrawler recruitCrawler;

    @BeforeEach
    void setUp() {
        recruitCrawler = new RecruitCrawler();
    }

    @DisplayName("URL로 채용 정보를 수집할 수 있어야 한다")
    @Test
    void collectRecruitmentsTest() {
        recruitCrawler.collectRecruitments();
    }
}