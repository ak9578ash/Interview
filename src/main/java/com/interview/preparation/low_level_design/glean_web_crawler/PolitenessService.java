package com.interview.preparation.low_level_design.glean_web_crawler;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class PolitenessService {
  private final Map<String, Integer> domainToCrawlDelay;
  private final Map<String, LocalDateTime> domainToLastCrawlTime;
  private final Integer defaultCrawlDelay;

  public PolitenessService(Integer defaultCrawlDelay) {
    this.domainToCrawlDelay = new HashMap<>();
    this.domainToLastCrawlTime = new HashMap<>();
    this.defaultCrawlDelay = defaultCrawlDelay;

    this.domainToCrawlDelay.put("en.wikipedia.org", 5);
  }

  public boolean isPolite(String urlString) throws MalformedURLException {
    URL url = new URL(urlString);
    String domain = url.getHost();
    LocalDateTime currentTime = LocalDateTime.now();

    if (domainToLastCrawlTime.containsKey(domain)) {
      LocalDateTime lastCrawlTime = domainToLastCrawlTime.get(domain);
      Integer crawlDelay = domainToCrawlDelay.get(domain);

      if (currentTime.getSecond() - lastCrawlTime.getSecond() > crawlDelay) {
        domainToLastCrawlTime.put(domain, currentTime);
        return true;
      } else {
        return false;
      }
    } else {
      domainToLastCrawlTime.put(domain, currentTime);
      return true;
    }
  }
}




