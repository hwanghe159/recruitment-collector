package com.junho.recruitmentcollector.service;

import com.junho.recruitmentcollector.crawler.dynamicweb.naver.NaverCollectStrategy;
import com.junho.recruitmentcollector.domain.Company;
import com.junho.recruitmentcollector.domain.CompanyRepository;
import com.junho.recruitmentcollector.domain.Recruitment;
import com.junho.recruitmentcollector.domain.RecruitmentRepository;
import com.junho.recruitmentcollector.exception.CompanyNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @BeforeEach
    void setUp() {
        this.companyService = new CompanyService(companyRepository, recruitmentRepository);
    }

    @DisplayName("예외테스트: 해당하는 회사 id가 존재하지 않을 때 예외가 발생해야 한다")
    @Test
    void updateFailTest() {
        Long invalidCompanyId = -1L;

        when(companyRepository.findById(invalidCompanyId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> companyService.update(invalidCompanyId))
            .isInstanceOf(CompanyNotFoundException.class)
            .hasMessageEndingWith("에 해당하는 회사가 존재하지 않습니다.");
    }

//    @DisplayName("회사 id로 채용공고를 업데이트할 수 있어야 한다")
//    @Test
//    void updateTest() {
//        //URL collector로 가져온 url들은 업데이트해주거나 추가해야 하고
//        //나머지 애들은 마감처리 해줘야 한다
//        Long naverId = 1L;
//        Company naver = new Company(naverId, "네이버", NaverCollectStrategy.NAVER_URL);
//        Recruitment recruitment1 = new Recruitment(1L, "네이버채용1제목", "네이버채용1내용", "네이버채용1URL", naver, false);
//        Recruitment recruitment2 = new Recruitment(2L, "네이버채용2제목", "네이버채용2내용", "네이버채용2URL", naver, false);
//        Recruitment recruitment3 = new Recruitment(3L, "네이버채용3제목", "네이버채용3내용", "네이버채용3URL", naver, false);
//        Set<String> urls = new HashSet<>(Arrays.asList("네이버채용1URL", "네이버채용3URL"));
//
//        when(companyRepository.findById(naverId)).thenReturn(Optional.of(naver));
//        when(recruitmentRepository.findAllByCompany(naver)).thenReturn(Arrays.asList(recruitment1, recruitment2));
//        //when(Jsoup.connect("네이버채용1URL").get()).thenReturn(new Document())
//
//        companyService.update(naverId);
//
//        assertAll(
//            () -> verify(recruitment1).update("네이버채용1URL", "변경된네이버채용1내용"),
//            () -> assertThat(recruitment2.getIsClosed()).isTrue(),
//            //() -> verify(recruitmentRepository).saveAll()
//        );
//    }
}