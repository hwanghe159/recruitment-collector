package com.junho.recruitmentcollector.crawler.dynamicweb.naver;

import com.junho.recruitmentcollector.crawler.dynamicweb.DynamicWebPageUrlCrawler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class NaverCrawlerTest {

    private DynamicWebPageUrlCrawler crawler;

    @BeforeEach
    void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--no-sandbox");
        WebDriver driver = new ChromeDriver(chromeOptions);
        crawler = new DynamicWebPageUrlCrawler(driver, new NaverCollectStrategy(driver));
    }

    @DisplayName("네이버 채용공고의 URL을 불러올 수 있어야 한다.")
    @Test
    void collectTest() {
        Set<String> urls = crawler.collect();

        assertThat(urls).isNotEmpty();
    }
}