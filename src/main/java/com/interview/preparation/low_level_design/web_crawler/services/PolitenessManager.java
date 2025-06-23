package com.interview.preparation.low_level_design.web_crawler.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;

public class PolitenessManager {
  private final Map<String, LocalDateTime> hostToLastCrawlTime;
  private final Map<String, Long>hostToCrawlDelay;
  private final long defaultCrawlDelaySeconds;

  public PolitenessManager(long defaultCrawlDelaySeconds) {
    this.hostToLastCrawlTime = new ConcurrentHashMap<>();
    this.hostToCrawlDelay = new ConcurrentHashMap<>();
    this.defaultCrawlDelaySeconds = defaultCrawlDelaySeconds;
  }

  public boolean isPolite(String url) {
    try {
      URI uri = new URI(url);
      String host = uri.getHost();
      if (hostToLastCrawlTime.containsKey(host)) {
        LocalDateTime lastCrawlTime = hostToLastCrawlTime.get(host);
        LocalDateTime currentTime = LocalDateTime.now();
        Long crawlDelay = hostToCrawlDelay.get(host);
        if (currentTime.getSecond() - lastCrawlTime.getSecond() >= crawlDelay) {
          hostToLastCrawlTime.put(host, currentTime);
          return true;
        }
        return false;
      } else {
        String robotsUrl = "https://" + uri.getHost() + "/robots.txt";
        String doc = Jsoup.connect(robotsUrl).get().body().text();
        Matcher m = Pattern.compile("(?i)Crawl-delay:\\s*(\\d+)").matcher(doc);
        if (m.find()) {
          long crawlDelay = Long.parseLong(m.group(1));
          hostToCrawlDelay.put(host, crawlDelay);
        } else {
          hostToCrawlDelay.put(host, defaultCrawlDelaySeconds);
        }
        hostToLastCrawlTime.put(host, LocalDateTime.now());
        return true;
      }
    } catch (URISyntaxException | IOException e) {
      return false;
    }
  }
}
