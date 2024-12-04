package com.interview.preparation.multi_threading.questions.build_molecule;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class H2OMachine {
  private final StringBuilder sb;
  private String molecule;

  public H2OMachine() {
    this.sb = new StringBuilder(3);
    this.molecule = "";
  }

  public synchronized void hydrogenAtom() throws InterruptedException {
    while (getFrequency(sb, 'H') == 2) {
      this.wait();
    }

    sb.append('H');
    log.info("Hydrogen Added: {}", sb);
    if (sb.length() == 3) {
      molecule = sb.toString();
      sb.delete(0, sb.length());
    }
    this.notifyAll();
  }

  public synchronized void oxygenAtom() throws InterruptedException {
    while (getFrequency(sb, 'O') == 1) {
      this.wait();
    }

    sb.append('O');
    log.info("Oxygen Added: {}", sb);
    if (sb.length() == 3) {
      molecule = sb.toString();
      sb.delete(0, sb.length());
    }
    this.notifyAll();
  }

  private int getFrequency(StringBuilder sb, char targetChar) {
    int count = 0;
    for (int i = 0; i < sb.length(); i++) {
      if (sb.charAt(i) == targetChar) {
        count++;
      }
    }
    return count;
  }
}
