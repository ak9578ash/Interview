package com.interview.preparation.low_level_design.testing;

import com.interview.preparation.low_level_design.testing.models.Link;
import com.interview.preparation.low_level_design.testing.repository.RawHtmlStore;
import com.interview.preparation.low_level_design.testing.services.PolitenessManager;
import com.interview.preparation.low_level_design.testing.services.UrlContentDeDuplicator;
import com.interview.preparation.low_level_design.testing.services.UrlExtractorService;
import com.interview.preparation.low_level_design.testing.services.UrlParserService;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class TestingMain {
  private static final int NUM_CRAWLERS = 5;

  public static void main(String[] args) throws InterruptedException {
    String seed = "https://medium.com/@hnasr/postgresql-process-architecture-f21e16459907";
    int maxDepth = 3;

    Queue<Link> queue = new ConcurrentLinkedQueue<>();
    queue.add(new Link(seed, 0));

    PolitenessManager politenessManager = new PolitenessManager(2);
    RawHtmlStore htmlStore = new RawHtmlStore();
    UrlContentDeDuplicator urlContentDeDuplicator = new UrlContentDeDuplicator();
    UrlExtractorService urlExtractorService = new UrlExtractorService(htmlStore, urlContentDeDuplicator);
    UrlParserService urlParserService = new UrlParserService(htmlStore);

    ThreadFactory threadFactory = Thread.ofVirtual().name("crawler-", 0).factory();
    ExecutorService executorService = Executors.newThreadPerTaskExecutor(threadFactory);

    for (int i = 0; i < NUM_CRAWLERS; i++) {
      executorService.submit(() -> {
            while (!queue.isEmpty()) {
              Link link = queue.poll();
              if (link == null) {
                continue;
              }

              if (link.getDepth() > maxDepth) {
                continue;
              }
              if (htmlStore.has(link.getUrl())) {
                continue;
              }

              if (!politenessManager.isPolite(link.getUrl())) {
                try {
                  Thread.sleep(500); // Delay before skipping; allows reattempts later
                } catch (InterruptedException e) {
                  Thread.currentThread().interrupt(); // Respect interruption
                  break; // Exit thread if interrupted
                }
                queue.add(link); // Optionally requeue it to try again later
                continue;
              }

              Optional<String> rawHtml = urlExtractorService.fetchAndStore(link.getUrl());

              rawHtml.ifPresent(html -> {
                //TODO:store the rawHtmlText in the RawHtmlTextStore
                String rawHtmlText = urlParserService.parse(link.getUrl());
                List<Link> newLinks = urlExtractorService.extractLinks(link.getUrl(), html, link.getDepth());
                queue.addAll(newLinks);
              });
            }
      });
    }

    Thread.sleep(10000000); // Wait for all tasks to complete
  }
}
