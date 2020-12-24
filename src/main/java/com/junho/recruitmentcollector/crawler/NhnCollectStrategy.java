package com.junho.recruitmentcollector.crawler;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
