package com.junho.recruitmentcollector.crawler.dynamicweb;

import com.junho.recruitmentcollector.crawler.CollectStrategy;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

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
