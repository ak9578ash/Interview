package com.interview.preparation.multi_threading.custom_read_write_lock;

import lombok.extern.slf4j.Slf4j;

/**
 * Conditions for given read or write access
 * Read Access    If no threads are writing, and no threads have requested write access.
 * Write Access   If no threads are reading or writing.
 */
@Slf4j
public class ReadWriteLockWithoutReentrance {
  private int readers;
  private int writers;
  private int writeRequests;

  public ReadWriteLockWithoutReentrance() {
    this.writers = 0;
    this.readers = 0;
    this.writeRequests = 0;
  }

  public synchronized void readLock() {
    while (writers > 0 || writeRequests > 0) {
      try {
        log.info("Waiting for read lock");
        this.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    readers++;
  }

  public synchronized void readUnlock() {
    readers--;
    notifyAll();
    log.info("Read unlock");
  }

  public synchronized void writeLock() { // this writeLock in not reentrant
    writeRequests++;
    while (readers > 0 || writers > 0) {
      try {
        log.info("Waiting for write lock");
        this.wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    writeRequests--;
    writers++;
  }

  public synchronized void writeUnlock() {
    writers--;
    notifyAll();
    log.info("Write unlock");
  }
}
