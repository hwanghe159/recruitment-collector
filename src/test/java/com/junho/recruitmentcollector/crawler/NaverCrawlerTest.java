package com.junho.recruitmentcollector.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NaverCrawlerTest {

    private RecruitmentCrawler crawler;

    @BeforeEach
    void setUp() {
        crawler = new NaverCrawler();
    }

    @DisplayName("네이버 채용공고의 URL을 불러올 수 있어야 한다.")
    @Test
    void collectTest() {
        Set<String> urls = crawler.collect();

        assertThat(urls).isNotEmpty();
    }
}