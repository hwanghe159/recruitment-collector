package com.junho.recruitmentcollector.crawler.dynamicweb.naver;

import com.junho.recruitmentcollector.crawler.CollectStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

@Component
public class NaverCollectStrategy implements CollectStrategy {

    public static final String NAVER_START_URL = "https://recruit.navercorp.com/naver/job/list/developer";

    private final WebDriver driver;

    public NaverCollectStrategy(WebDriver driver) {
        this.driver = driver;
    }

    public Set<String> collect() {
        driver.get(NAVER_START_URL);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(By.className("crd_tit")));
        driver.findElement(By.className("more_btn")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='card_list']/ul/li/a"));
        return elements.stream()
            .map(element -> element.getAttribute("href"))
            .collect(Collectors.toSet());
    }
}
