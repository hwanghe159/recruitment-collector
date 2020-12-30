package com.junho.recruitmentcollector.service;

import com.junho.recruitmentcollector.domain.CompanyRepository;
import com.junho.recruitmentcollector.domain.RecruitmentRepository;
import com.junho.recruitmentcollector.exception.CompanyNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

    @DisplayName("회사 id로 채용공고를 업데이트할 수 있어야 한다")
    @Test
    void updateTest() {
    }
}