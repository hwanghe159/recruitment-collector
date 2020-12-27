package com.junho.recruitmentcollector.crawler.staticweb;

import com.junho.recruitmentcollector.crawler.CollectStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class StaticWebPageUrlCrawler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private CollectStrategy collectStrategy;

    public StaticWebPageUrlCrawler(CollectStrategy collectStrategy) {
        this.collectStrategy = collectStrategy;
    }

    public Set<String> collect() {
        Set<String> result = collectStrategy.collect();

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
