package com.interview.preparation.low_level_design.google_doc;

public class Cursor {
  private int position;

  public Cursor(int position) {
    this.position = position;
  }

  public int getPosition() {
    return position;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}
