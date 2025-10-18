package com.interview.preparation.low_level_design.glean_web_crawler;

import java.io.IOException;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@AllArgsConstructor
public class UrlRenderService {
  public String extractRawHTML(String url) throws IOException {
    Document document = Jsoup.connect(url).get();
    return document.html();
  }
}
