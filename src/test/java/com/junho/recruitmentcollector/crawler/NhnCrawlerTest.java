package com.junho.recruitmentcollector.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NhnCrawlerTest {

    private UrlCrawler crawler;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        crawler = new UrlCrawler(driver, new NhnCollectStrategy(driver));
    }

    @DisplayName("NHN 채용공고의 URL을 불러올 수 있어야 한다.")
    @Test
    void collectTest() {
        Set<String> urls = crawler.collect();

        assertThat(urls).isNotEmpty();
    }
}