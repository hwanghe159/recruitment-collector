package com.junho.recruitmentcollector.crawler.staticweb.nhn;

import com.junho.recruitmentcollector.crawler.CollectStrategy;
import com.junho.recruitmentcollector.crawler.staticweb.StaticWebPageConnector;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NhnCollectStrategy implements CollectStrategy {

    public static final String NHN_URL = "https://recruit.nhn.com/ent/recruitings?type=class";
    public static final String NHN_RECRUITMENT_URL_PREFIX = "https://recruit.nhn.com";

    private final StaticWebPageConnector staticWebPageConnector;
    private final NhnHrefExtractor nhnHrefExtractor;

    public NhnCollectStrategy(StaticWebPageConnector staticWebPageConnector, NhnHrefExtractor nhnHrefExtractor) {
        this.staticWebPageConnector = staticWebPageConnector;
        this.nhnHrefExtractor = nhnHrefExtractor;
    }

    public Set<String> collect() {
        Document doc = staticWebPageConnector.connect(NHN_URL);

        List<String> links = nhnHrefExtractor.extract(doc);

        return links.stream()
            .map(NHN_RECRUITMENT_URL_PREFIX::concat)
            .collect(Collectors.toSet());
    }
}
