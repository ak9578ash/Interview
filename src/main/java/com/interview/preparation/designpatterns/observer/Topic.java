package com.interview.preparation.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;

@Getter
public class Topic implements Subject {
  private String message;
  private final List<Observer> observerList;
  private Boolean changed;
  private final String name;

  public Topic(String name) {
    this.message = "INITIAL_MESSAGE";
    this.changed = false;
    this.observerList = new ArrayList<>();
    this.name = name;
  }

  @Override
  public void addObserver(Observer observer) {
    this.observerList.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    this.observerList.remove(observer);
  }

  @Override
  public void notifyObserver() {
    if (changed.equals(true)) {
      for (Observer observer : observerList) {
        observer.update(message);
      }
    }
  }

  public void changeData() {
    this.message = UUID.randomUUID().toString();
    this.changed = true;
    notifyObserver();
  }
}
