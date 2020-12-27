package com.junho.recruitmentcollector.crawler.staticweb;

import com.junho.recruitmentcollector.exception.ConnectionFailedException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class StaticWebPageConnector {

    public StaticWebPageConnector() {
    }

    public Document connect(String url) {
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException | IllegalArgumentException e) {
            throw new ConnectionFailedException(url);
        }
        return doc;
    }
}
