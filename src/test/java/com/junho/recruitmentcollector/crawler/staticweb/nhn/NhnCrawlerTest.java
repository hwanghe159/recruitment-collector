package com.junho.recruitmentcollector.crawler.staticweb.nhn;

import com.junho.recruitmentcollector.crawler.staticweb.StaticWebPageConnector;
import com.junho.recruitmentcollector.crawler.staticweb.StaticWebPageUrlCrawler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NhnCrawlerTest {

    private StaticWebPageUrlCrawler crawler;

    @BeforeEach
    void setUp() {
        crawler = new StaticWebPageUrlCrawler(new NhnCollectStrategy(new StaticWebPageConnector(), new NhnHrefExtractor()));
    }

    @DisplayName("NHN 채용공고의 URL을 불러올 수 있어야 한다.")
    @Test
    void collectTest() {
        Set<String> urls = crawler.collect();

        assertThat(urls).isNotEmpty();
    }
}