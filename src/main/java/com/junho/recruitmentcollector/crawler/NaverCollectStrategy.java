package com.junho.recruitmentcollector.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NaverCollectStrategy implements CollectStrategy {

    public static final String NAVER_START_URL = "https://recruit.navercorp.com/naver/job/list/developer";
    public static final String NAVER_URL_BOUNDARY = "https://recruit.navercorp.com";
    public static final String NAVER_RECRUITMENT_URL_PREFIX = "https://recruit.navercorp.com/naver/job/detail/developer?annoId=";

    private final WebDriver driver;

    public NaverCollectStrategy(WebDriver driver) {
        this.driver = driver;
    }

    public Set<String> collect() {
        Queue<String> queue = new LinkedList<>();
        Set<String> result = new HashSet<>();

        queue.add(NAVER_START_URL);
        while (!queue.isEmpty()) {
            String crawledUrl = queue.poll();
            System.out.println(crawledUrl + "로부터 크롤링 시작");
            Set<String> urls = extractAllLinksAfterFullyLoaded(crawledUrl);
            for (String url : urls) {
                if (!result.contains(url)) {
                    if (url.startsWith(NAVER_RECRUITMENT_URL_PREFIX)) {
                        result.add(url);
                        System.out.println("결과에 추가: " + url);
                    }
                    queue.add(url);
                }
            }
        }
        return new HashSet<>(result);
    }

    private Set<String> extractAllLinksAfterFullyLoaded(String rootUrl) {
        driver.get(rootUrl);
        try {
            new WebDriverWait(driver, 5)
                .until(ExpectedConditions.or(
                    presenceOfElementLocated(By.className("crd_tit")),
                    presenceOfElementLocated(By.className("context_area")))
                );
        } catch (TimeoutException e) {
        }
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        return elements.stream()
            .map(element -> element.getAttribute("href"))
            .filter(link -> link.startsWith(NAVER_URL_BOUNDARY))
            .collect(Collectors.toSet());
    }

}
