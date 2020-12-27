package com.junho.recruitmentcollector.crawler.dynamicweb.naver;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class NaverCollectStrategyTest {

    private static final int PORT = 9000;
    private static ClientAndServer MOCK_SERVER;

    private WebDriver driver;
    private NaverCollectStrategy naverCollectStrategy;

    @BeforeEach
    void setUp() {
        MOCK_SERVER = ClientAndServer.startClientAndServer(PORT);
        System.setProperty("webdriver.chrome.driver", "src/main/driver/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.naverCollectStrategy = new NaverCollectStrategy(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        MOCK_SERVER.stop();
    }

    @DisplayName("네이버 크롤링을 할 수 있어야 한다.")
    @Test
    void collectTest() {
        mockingResponseWith("naver_recruit.html");
        Set<String> urls = naverCollectStrategy.collect();

        assertAll(
            () -> assertThat(urls).hasSize(15),
            () -> assertThat(urls).allMatch(url -> url.startsWith("https://recruit.navercorp.com/naver/job/detail/developer?annoId="))
        );
    }

    private void mockingResponseWith(String filePath) {
        byte[] response = readHtmlFile(filePath);

        new MockServerClient("localhost", PORT)
            .when(
                request()
                    .withMethod("GET")
            )
            .respond(
                response()
                    .withStatusCode(200)
                    .withBody(response)
            );
    }

    private byte[] readHtmlFile(String filePath) {
        InputStream is = getClass()
            .getClassLoader()
            .getResourceAsStream(filePath);
        try {
            assert is != null;
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            throw new RuntimeException(String.format("%s 불러오기 실패", filePath));
        }
    }
}