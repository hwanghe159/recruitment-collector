package com.junho.recruitmentcollector.service;

import com.junho.recruitmentcollector.domain.Company;
import com.junho.recruitmentcollector.domain.CompanyRepository;
import com.junho.recruitmentcollector.domain.Recruitment;
import com.junho.recruitmentcollector.domain.RecruitmentRepository;
import com.junho.recruitmentcollector.exception.CompanyNotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    private RecruitmentRepository recruitmentRepository;

    public CompanyService(CompanyRepository companyRepository, RecruitmentRepository recruitmentRepository) {
        this.companyRepository = companyRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public void update(Long companyId) {
        Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new CompanyNotFoundException(companyId));

        List<Recruitment> recruitments = recruitmentRepository.findAllByCompany(company);
        Set<String> urls = company.collectUrls();

        recruitments.stream()
            .filter(recruitment -> !urls.contains(recruitment.getUrl()))
            .forEach(Recruitment::close);

        List<Recruitment> newRecruitments = new ArrayList<>();
        try {
            for (String url : urls) {
                Document document = Jsoup.connect(url).get();
                String title = document.title();
                String content = document.text();
                Optional<Recruitment> re = recruitments
                    .stream()
                    .filter(recruitment -> recruitment.getUrl().equals(url))
                    .findFirst();
                if (re.isPresent()) {
                    re.get().update(title, content);
                } else {
                    newRecruitments.add(new Recruitment(title, content, url, company));
                }
            }
        } catch (IOException e) {
            System.out.println("에러발생");
        }
        recruitmentRepository.saveAll(newRecruitments);
    }
}
