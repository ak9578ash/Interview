package com.interview.preparation.low_level_design.glean_web_crawler;


import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@AllArgsConstructor
public class UrlExtractorService {
  public List<String> extractUrls(String rawHtml) {
    List<String> extractedUrls = new ArrayList<>();

    Document htmlDocument = Jsoup.parse(rawHtml);
    Elements hrefTags = htmlDocument.select("a[href]");

    for (Element hrefTag : hrefTags) {
//      System.out.println("  Href: " + hrefTag.attr("href"));
//      System.out.println("  Text: " + hrefTag.text());
//      System.out.println("---------------");


      if (hrefTag.attr("href").startsWith("/wiki")) {
        extractedUrls.add("https://en.wikipedia.org/"+  hrefTag.attr("href"));
      }
    }
    System.out.println("Extracted URL Size: "+ extractedUrls.size());
    return extractedUrls;
  }

  public String extractTitle(String rawHtml) {
    Document htmlDocument = Jsoup.parse(rawHtml);
    return htmlDocument.title();
  }
}
