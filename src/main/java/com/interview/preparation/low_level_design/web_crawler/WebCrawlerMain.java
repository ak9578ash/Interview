package com.interview.preparation.low_level_design.web_crawler;

import com.interview.preparation.low_level_design.web_crawler.models.Link;
import com.interview.preparation.low_level_design.web_crawler.repository.RawHtmlStore;
import com.interview.preparation.low_level_design.web_crawler.services.PolitenessManager;
import com.interview.preparation.low_level_design.web_crawler.services.UrlContentDeDuplicator;
import com.interview.preparation.low_level_design.web_crawler.services.UrlExtractorService;
import com.interview.preparation.low_level_design.web_crawler.services.UrlParserService;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class WebCrawlerMain {
  public static void main(String[] args) {
    String seed = "https://en.wikipedia.org/wiki/Web_crawler";
    int maxDepth = 2;

    Queue<Link> queue = new LinkedList<>();
    queue.add(new Link(seed, 0));

    PolitenessManager politeness = new PolitenessManager(2000); // 2s delay
    RawHtmlStore htmlStore = new RawHtmlStore();
    UrlContentDeDuplicator deDup = new UrlContentDeDuplicator();
    UrlExtractorService extractor = new UrlExtractorService(politeness, htmlStore, deDup);
    UrlParserService parser = new UrlParserService(htmlStore);

    while (!queue.isEmpty()) {
      Link link = queue.poll();

      if (link.getDepth() > maxDepth) {
        continue;
      }
      if (htmlStore.has(link.getUrl())) {
        continue;
      }

      Optional<String> htmlOpt = extractor.fetchAndStore(link.getUrl());
      htmlOpt.ifPresent(html -> {
        parser.parse(link.getUrl());
        List<Link> newLinks = extractor.extractLinks(link.getUrl(), html, link.getDepth());
        queue.addAll(newLinks);
      });
    }
  }
}
