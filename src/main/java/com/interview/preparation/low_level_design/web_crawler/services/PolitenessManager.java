package com.interview.preparation.low_level_design.web_crawler.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PolitenessManager {
  private final Map<String, LocalDateTime> hostToLastCrawlTime;
  private final long crawlDelayMillis;

  public PolitenessManager(long crawlDelayMillis) {
    this.hostToLastCrawlTime = new HashMap<>();
    this.crawlDelayMillis = crawlDelayMillis;
  }

  boolean isPolite(String url) {
    try {
      URI uri = new URI(url);
      String host = uri.getHost();
      if (hostToLastCrawlTime.containsKey(host)) {
        // If the host is already in the map, check the last crawl time
        LocalDateTime lastCrawlTime = hostToLastCrawlTime.get(host);
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.getSecond() - lastCrawlTime.getSecond() >= crawlDelayMillis / 1000) {
          hostToLastCrawlTime.put(host, currentTime);
          return true;
        }
        return false;
      } else {
        //TODO: Fetch the politeness of the host using the robots.txt file
        return true;
      }
    } catch (URISyntaxException e) {
      return false;
    }
  }
}
