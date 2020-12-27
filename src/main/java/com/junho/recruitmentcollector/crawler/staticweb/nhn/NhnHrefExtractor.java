package com.junho.recruitmentcollector.crawler.staticweb.nhn;

import com.junho.recruitmentcollector.exception.ExtractFailedException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Selector;

import java.util.List;

public class NhnHrefExtractor {

    public NhnHrefExtractor() {
    }

    public List<String> extract(Document doc) {
        List<String> links;
        try {
            links = doc.select("table:contains(기술 모집분야)").get(0)
                .select("tbody tr td p a")
                .eachAttr("href");
        } catch (Selector.SelectorParseException | IndexOutOfBoundsException e) {
            throw new ExtractFailedException("NHN");
        }
        return links;
    }
}
