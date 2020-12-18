package com.junho.recruitmentcollector.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class NaverCrawler implements RecruitmentCrawler {

    public static final String NAVER_URL = "https://recruit.navercorp.com/naver/job/list/developer";
    public static final String NAVER_RECRUITMENT_URL_PREFIX = "https://recruit.navercorp.com/naver/job/detail/developer?annoId=";

    public Set<String> collect() {
        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        Queue<String> queue = new LinkedList<>();
        Set<String> result = new HashSet<>();

        queue.add(NAVER_URL);
        while (!queue.isEmpty()) {
            String crawledUrl = queue.poll();
            System.out.println("\n여기서부터 크롤링시작: " + crawledUrl);
            Set<String> strings = extractAllLinksAfterFullyLoaded(crawledUrl, driver);
            for (String string : strings) {
                if (!result.contains(string)) {
                    result.add(string);
                    System.out.println("Sited added for crawling : " + string);
                    queue.add(string);
                }
            }
        }
        driver.close();
        return new HashSet<>(result);
    }

    private Set<String> extractAllLinksAfterFullyLoaded(String rootUrl, WebDriver driver) {
        driver.get(rootUrl);
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.or(
                presenceOfElementLocated(By.className("crd_tit")),
                presenceOfElementLocated(By.className("context_area")))
            );
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        return elements.stream()
            .map(element -> element.getAttribute("href"))
            .filter(link -> link.startsWith(NAVER_RECRUITMENT_URL_PREFIX))
            .collect(Collectors.toSet());
    }
}
