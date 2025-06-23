package com.interview.preparation.low_level_design.testing.services;

import com.interview.preparation.low_level_design.testing.repository.RawHtmlStore;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@Slf4j
public class UrlParserService {
  private final RawHtmlStore htmlStore;

  public UrlParserService(RawHtmlStore htmlStore) {
    this.htmlStore = htmlStore;
  }

  public String parse(String url) {
    String html = htmlStore.get(url);
    if (html == null) {
      return Strings.EMPTY;
    }

    Document doc = Jsoup.parse(html);
    String text = doc.body().text();
    log.info("Parsed content for: " + url);
    log.info("Snippet: {}", text.substring(0, Math.min(100, text.length())) + "...\n");
    return text;
  }
}
