package com.interview.preparation.low_level_design.glean_web_crawler;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class DeDuplicatorService {
  private Set<String> visitedUrls;
  private Set<Integer> rawHtmlHash;

  public DeDuplicatorService() {
    this.visitedUrls = new HashSet<>();
    this.rawHtmlHash = new HashSet<>();
  }

  public boolean isDuplicate(String url, String rawHTML) {
    Integer htmlHash = rawHTML.hashCode();
    if (visitedUrls.contains(url) || rawHtmlHash.contains(htmlHash)) {
      log.warn("URL {} or the RawHTML is already scraped", url);
      return true;
    }

    visitedUrls.add(url);
    rawHtmlHash.add(htmlHash);
    return false;
  }
}
