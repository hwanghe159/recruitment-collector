package com.junho.recruitmentcollector.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Recruitment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String url;

    @ManyToOne(fetch = LAZY)
    private Company company;

    private Boolean isClosed;

    public Recruitment(Long id, String title, String content, String url, Company company, Boolean isClosed) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.company = company;
        this.isClosed = isClosed;
    }

    public Recruitment(String title, String content, String url, Company company) {
        this(null, title, content, url, company, false);
    }

    public void close() {
        this.isClosed = true;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
