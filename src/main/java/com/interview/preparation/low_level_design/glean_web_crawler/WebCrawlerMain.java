package com.interview.preparation.low_level_design.glean_web_crawler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebCrawlerMain {
  public static void main(String[] args) throws IOException {
    String seedUrl = "https://en.wikipedia.org/wiki/Google";
    int maxNumPagesToVisit = 3;

    Queue<String>crawlerQueue = new LinkedList<>();
    UrlExtractorService urlExtractorService = new UrlExtractorService();
    DeDuplicatorService deDuplicatorService = new DeDuplicatorService();
    UrlRenderService urlRenderService  = new UrlRenderService();
    PolitenessService politenessService = new PolitenessService(2);

    List<Output> outputList = new ArrayList<>();

    crawlerQueue.add(seedUrl);
    while(!crawlerQueue.isEmpty()) {
      String url = crawlerQueue.poll();

      if (url == null) {
        break;
      }

      if (!politenessService.isPolite(url)) {
        log.info("Cannot Fetch URL {} as it is not respecting politenes", url);
        try{
          Thread.sleep(50);
        }catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
        crawlerQueue.add(url);
        continue;
      }

      String rawHtml;
      try {
        rawHtml = urlRenderService.extractRawHTML(url);
      } catch (IOException e) {
        log.error("Error fetching the URL: {}", url, e.getMessage());
        continue;
      }


      if (deDuplicatorService.isDuplicate(url, rawHtml)) {
        continue;
      }

      if (outputList.size() >= maxNumPagesToVisit) {
        log.info("Maximum Pages Limit Reached");
        break;
      }

      outputList.add(
          new Output(url, urlExtractorService.extractTitle(rawHtml))
      );


      List<String> nextSeedUrls = urlExtractorService.extractUrls(rawHtml);

      crawlerQueue.addAll(nextSeedUrls);
    }

    for(int i=0;i<outputList.size();i++) {
      System.out.println("Url: "+ outputList.get(i).getUrl());
      System.out.println("Title: "+ outputList.get(i).getTitle());

      System.out.println("-------------------");
    }
  }
}
