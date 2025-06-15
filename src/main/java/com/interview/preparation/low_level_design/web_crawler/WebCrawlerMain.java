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
    int maxDepth = 3;

    Queue<Link> queue = new LinkedList<>();
    queue.add(new Link(seed, 0));

    PolitenessManager politenessManager = new PolitenessManager(2000); // 2s delay
    RawHtmlStore htmlStore = new RawHtmlStore();
    UrlContentDeDuplicator urlContentDeDuplicator = new UrlContentDeDuplicator();
    UrlExtractorService urlExtractorService = new UrlExtractorService(htmlStore, urlContentDeDuplicator);
    UrlParserService urlParserService = new UrlParserService(htmlStore);

    while (!queue.isEmpty()) {
      Link link = queue.poll();

      if (link.getDepth() > maxDepth) {
        continue;
      }
      if (htmlStore.has(link.getUrl())) {
        continue;
      }

      if (!politenessManager.isPolite(link.getUrl())) {
        continue;
      }

      Optional<String> rawHtml = urlExtractorService.fetchAndStore(link.getUrl());

      rawHtml.ifPresent(html -> {
        //TODO:store the rawHtmlText in the RawHtmlTextStore
        String rawHtmlText = urlParserService.parse(link.getUrl());
        List<Link> newLinks = urlExtractorService.extractLinks(link.getUrl(), html, link.getDepth());
        queue.addAll(newLinks);
      });

    }
  }
}
