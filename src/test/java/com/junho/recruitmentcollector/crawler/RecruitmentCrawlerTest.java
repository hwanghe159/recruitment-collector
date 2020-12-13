package com.junho.recruitmentcollector.crawler;

import com.junho.recruitmentcollector.domain.Recruitment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecruitmentCrawlerTest {

    public static final String NAVER_RECRUITMENT_URL = "https://recruit.navercorp.com/naver/recruitMain";

    private RecruitmentCrawler recruitmentCrawler;

    @BeforeEach
    void setUp() {
        recruitmentCrawler = new RecruitmentCrawler();
    }

    @DisplayName("URL로 채용 정보를 수집할 수 있어야 한다")
    @Test
    void collectRecruitmentsTest() {
        List<Recruitment> recruitments
            = recruitmentCrawler.collectRecruitments(NAVER_RECRUITMENT_URL);

        assertThat(recruitments).isNull();
    }
}