package com.junho.recruitmentcollector.crawler;

import com.junho.recruitmentcollector.crawler.dynamicweb.naver.NaverCollectStrategy;
import com.junho.recruitmentcollector.crawler.staticweb.nhn.NhnCollectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CollectStrategies {

    public static final Map<String, CollectStrategy> COLLECT_STRATEGIES = new HashMap<>();

    @Autowired
    private NaverCollectStrategy naverCollectStrategy;

    @Autowired
    private NhnCollectStrategy nhnCollectStrategy;

    public CollectStrategies() {
        COLLECT_STRATEGIES.put(NaverCollectStrategy.NAVER_URL, naverCollectStrategy);
        COLLECT_STRATEGIES.put(NhnCollectStrategy.NHN_URL, nhnCollectStrategy);
    }

    public CollectStrategy findByUrl(String url) {
        return COLLECT_STRATEGIES.get(url);
    }
}
