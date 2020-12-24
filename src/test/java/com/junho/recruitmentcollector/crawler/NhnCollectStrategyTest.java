package com.junho.recruitmentcollector.crawler;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static com.junho.recruitmentcollector.crawler.NhnCollectStrategy.NHN_RECRUITMENT_URL_PREFIX;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

class NhnCollectStrategyTest {

    private static final int PORT = 9000;
    private static ClientAndServer MOCK_SERVER;

    private StaticWebPageConnector staticWebPageConnector;

    private NhnHrefExtractor nhnHrefExtractor;

    private NhnCollectStrategy nhnCollectStrategy;

    @BeforeEach
    void setUp() {
        MOCK_SERVER = ClientAndServer.startClientAndServer(PORT);
        this.staticWebPageConnector = new StaticWebPageConnector();
        this.nhnHrefExtractor = new NhnHrefExtractor();
        this.nhnCollectStrategy = new NhnCollectStrategy(staticWebPageConnector, nhnHrefExtractor);
    }

    @AfterEach
    void tearDown() {
        MOCK_SERVER.stop();
    }

    @DisplayName("NHN 크롤링을 할 수 있어야 한다.")
    @Test
    void collectTest() {
        mockingResponseWith("nhn_recruit.html");
        Set<String> urls = nhnCollectStrategy.collect();

        assertAll(
            () -> assertThat(urls).hasSize(42),
            () -> assertThat(urls).allMatch(url -> url.startsWith(NHN_RECRUITMENT_URL_PREFIX))
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