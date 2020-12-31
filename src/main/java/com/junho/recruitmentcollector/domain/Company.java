package com.junho.recruitmentcollector.domain;

import com.junho.recruitmentcollector.crawler.CollectStrategy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

import static com.junho.recruitmentcollector.crawler.CollectStrategies.COLLECT_STRATEGIES;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
public class Company extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String url;

    public Company(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public Company(String name, String url) {
        this(null, name, url);
    }

    public Set<String> collectUrls() {
        CollectStrategy collectStrategy = COLLECT_STRATEGIES.get(this.url);
        return collectStrategy.collect();
    }
}
