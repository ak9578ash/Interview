package com.interview.preparation.low_level_design.web_crawler.services;

import java.util.HashSet;
import java.util.Set;

public class UrlContentDeDuplicator {
  private final Set<String> visitedUrls = new HashSet<>();
  private final Set<Integer> contentHashes = new HashSet<>();

  //TODO: could add the strategy pattern to allow different de-duplication strategies rather then using the default hashCode
  boolean isDuplicate(String url, String content) {
    int hash = content.hashCode();
    if (visitedUrls.contains(url) || contentHashes.contains(hash)) {
      return true;
    }
    visitedUrls.add(url);
    contentHashes.add(hash);
    return false;
  }
}