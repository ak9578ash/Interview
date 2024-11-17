package com.interview.preparation.multi_threading.custom_read_write_lock;

/**
 * Conditions for given read or write access
 * Read Access    If no threads are writing, and no threads have requested write access.
 * Write Access   If no threads are reading or writing.
 */
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
  }

  public synchronized void writeLock() { // this writeLock in not reentrant
    writeRequests++;
    while (readers > 0 || writers > 0) {
      try {
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
  }
}
