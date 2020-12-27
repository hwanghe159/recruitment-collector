package com.junho.recruitmentcollector.crawler.staticweb.nhn;

import com.junho.recruitmentcollector.exception.ExtractFailedException;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NhnHrefExtractorTest {

    private NhnHrefExtractor nhnHrefExtractor;

    @BeforeEach
    void setUp() {
        this.nhnHrefExtractor = new NhnHrefExtractor();
    }

    @DisplayName("NHN 요소로 크롤링 작업을 실패했을때 예외를 발생시켜야 한다")
    @Test
    void collectFailedTest2() {
        assertThatThrownBy(() -> nhnHrefExtractor.extract(new Document("")))
            .isInstanceOf(ExtractFailedException.class);
    }
}