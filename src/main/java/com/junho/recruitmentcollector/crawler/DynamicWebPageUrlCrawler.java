package com.junho.recruitmentcollector.crawler;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DynamicWebPageUrlCrawler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private WebDriver driver;
    private CollectStrategy collectStrategy;

    public DynamicWebPageUrlCrawler(WebDriver driver, CollectStrategy collectStrategy) {
        this.driver = driver;
        this.collectStrategy = collectStrategy;
    }

    public Set<String> collect() {
        Set<String> result = collectStrategy.collect();

        driver.close();
        logResult(result);
        return new HashSet<>(result);
    }

    protected void logResult(Set<String> result) {
        logger.info("=====크롤링 결과=====");
        logger.info("크롤링한 링크 총 개수 : {}", result.size());
        for (String url : result) {
            logger.info(url);
        }
    }
}
