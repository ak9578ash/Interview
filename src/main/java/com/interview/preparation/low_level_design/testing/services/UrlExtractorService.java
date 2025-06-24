package com.interview.preparation.low_level_design.testing.services;

import com.interview.preparation.low_level_design.testing.exception.UrlFetchException;
import com.interview.preparation.low_level_design.testing.models.Link;
import com.interview.preparation.low_level_design.testing.repository.RawHtmlStore;
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
  public Optional<String> fetchAndStore(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      String html = doc.html();

      htmlStore.store(url, html);
      log.info("Fetched: " + url);
      return Optional.of(html);
    } catch (IOException e) {
      log.error("Failed to fetch: " + url);
      throw new UrlFetchException("Failed to fetch: " + url, e);
    }
  }
}
