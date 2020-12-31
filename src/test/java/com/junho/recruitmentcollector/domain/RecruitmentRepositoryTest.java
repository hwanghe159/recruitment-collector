package com.junho.recruitmentcollector.domain;

import com.junho.recruitmentcollector.crawler.dynamicweb.naver.NaverCollectStrategy;
import com.junho.recruitmentcollector.crawler.staticweb.nhn.NhnCollectStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class RecruitmentRepositoryTest {

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Company naver;
    private Company nhn;
    private Recruitment naverRecruitment1;
    private Recruitment naverRecruitment2;
    private Recruitment nhnRecruitment1;
    private Recruitment nhnRecruitment2;

    @BeforeEach
    void setUp() {
        naver = new Company("네이버", NaverCollectStrategy.NAVER_URL);
        nhn = new Company("NHN", NhnCollectStrategy.NHN_URL);

        naverRecruitment1 = new Recruitment("네이버채용1제목", "네이버채용1본문", "네이버채용1URL", naver);
        naverRecruitment2 = new Recruitment("네이버채용2제목", "네이버채용2본문", "네이버채용2URL", naver);
        nhnRecruitment1 = new Recruitment("NHN채용1제목", "NHN채용1본문", "NHN채용1URL", nhn);
        nhnRecruitment2 = new Recruitment("NHN채용2제목", "NHN채용2본문", "NHN채용2URL", nhn);

        companyRepository.saveAll(Arrays.asList(naver, nhn));
        recruitmentRepository.saveAll(Arrays.asList(naverRecruitment1, naverRecruitment2, nhnRecruitment1, nhnRecruitment2));
    }

    @DisplayName("특정 회사의 채용공고를 조회할 수 있어야 한다")
    @Test
    void findAllByCompanyTest() {
        List<Recruitment> naverRecruitments = recruitmentRepository.findAllByCompany(naver);

        assertAll(
            () -> assertThat(naverRecruitments).hasSize(2),
            () -> assertThat(naverRecruitments).containsExactlyInAnyOrder(naverRecruitment1, naverRecruitment2)
        );
    }
}