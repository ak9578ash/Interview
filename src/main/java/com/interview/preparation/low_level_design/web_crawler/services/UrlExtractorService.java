package com.interview.preparation.low_level_design.web_crawler.services;

import com.interview.preparation.low_level_design.web_crawler.models.Link;
import com.interview.preparation.low_level_design.web_crawler.repository.RawHtmlStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Slf4j
@AllArgsConstructor
public class UrlExtractorService {
  private final RawHtmlStore htmlStore;
  private final UrlContentDeDuplicator deDuplicator;

  public Optional<String> fetchAndStore(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      String html = doc.html();
      if (deDuplicator.isDuplicate(url, html)) {
        return Optional.empty();
      }

      htmlStore.store(url, html);
      log.info("Fetched: " + url);
      return Optional.of(html);
    } catch (IOException e) {
      log.error("Failed to fetch: " + url);
      return Optional.empty();
    }
  }

  public List<Link> extractLinks(String baseUrl, String html, int depth) {
    List<Link> links = new ArrayList<>();
    Document doc = Jsoup.parse(html, baseUrl);
    Elements anchorTags = doc.select("a[href]");
    for (Element a : anchorTags) {
      String href = a.attr("abs:href");
      links.add(new Link(href, depth + 1));
    }
    return links;
  }
}
