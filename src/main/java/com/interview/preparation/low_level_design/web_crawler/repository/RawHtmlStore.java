package com.interview.preparation.low_level_design.web_crawler.repository;

import java.util.HashMap;
import java.util.Map;

public class RawHtmlStore {
  private final Map<String, String> urlToHTML = new HashMap<>();

  public void store(String url, String html) {
    urlToHTML.put(url, html);
  }

  public String get(String url) {
    return urlToHTML.get(url);
  }

  public boolean has(String url) {
    return urlToHTML.containsKey(url);
  }
}
