//package com.interview.preparation.low_level_design.testing.services;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class UrlContentDeDuplicator {
//  private final Set<String> visitedUrls;
//  private final Set<Integer> contentHashes;
//
//  public UrlContentDeDuplicator() {
//    this.visitedUrls = new HashSet<>();
//    this.contentHashes = new HashSet<>();
//  }
//
//  //TODO:could add the strategy pattern to allow different de-duplication strategies rather then using the default hashCode
//  public boolean isDuplicate(String url, String content) {
//    int hash = content.hashCode();
//    if (visitedUrls.contains(url) || contentHashes.contains(hash)) {
//      return true;
//    }
//    visitedUrls.add(url);
//    contentHashes.add(hash);
//    return false;
//  }
//}