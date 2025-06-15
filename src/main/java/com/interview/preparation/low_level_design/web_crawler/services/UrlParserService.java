package com.interview.preparation.low_level_design.web_crawler.services;

import com.interview.preparation.low_level_design.web_crawler.repository.RawHtmlStore;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class UrlParserService {
  private final RawHtmlStore htmlStore;

  public UrlParserService(RawHtmlStore htmlStore) {
    this.htmlStore = htmlStore;
  }

  public void parse(String url) {
    String html = htmlStore.get(url);
    if (html == null) {
      return;
    }

    Document doc = Jsoup.parse(html);
    String text = doc.body().text();
    log.info("Parsed content for: " + url);
    log.info("Snippet: " + text.substring(0, Math.min(100, text.length())) + "...\n");
  }
}
