package com.interview.preparation.multi_threading.custom_read_write_lock;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLockWithReentrance {
  private final Map<Thread, Integer> readingThreads;
  private int writeRequests = 0;
  private int writeAccesses = 0;
  private Thread writingThread = null;

  public ReadWriteLockWithReentrance() {
    this.readingThreads = new HashMap<>();
    this.writeAccesses = 0;
    this.writeRequests = 0;
    this.writingThread = null;
  }


  public synchronized void lockRead() throws InterruptedException {
    Thread callingThread = Thread.currentThread();
    while (!canGrantReadAccess(callingThread)) {
      wait();
    }

    readingThreads.put(callingThread,
        (getReadAccessCount(callingThread) + 1));
  }

  private boolean canGrantReadAccess(Thread callingThread) {
    if (isWriter(callingThread)) {
      return true;
    }
    if (hasWriter()) {
      return false;
    }
    if (isReader(callingThread)) {
      return true;
    }
    if (hasWriteRequests()) {
      return false;
    }
    return true;
  }


  public synchronized void unlockRead() {
    Thread callingThread = Thread.currentThread();
    if (!isReader(callingThread)) {
      throw new IllegalMonitorStateException("Calling Thread does not hold a read lock on this ReadWriteLock");
    }
    int accessCount = getReadAccessCount(callingThread);
    if (accessCount == 1) {
      readingThreads.remove(callingThread);
      // Notify only when the last read lock is released.
      if (readingThreads.isEmpty()) {
        notifyAll();
      }
    } else {
      readingThreads.put(callingThread, accessCount - 1);
    }
  }

  public synchronized void lockWrite() throws InterruptedException {
    writeRequests++;
    Thread callingThread = Thread.currentThread();
    while (!canGrantWriteAccess(callingThread)) {
      wait();
    }
    writeRequests--;
    writeAccesses++;
    writingThread = callingThread;
  }

  public synchronized void unlockWrite() throws InterruptedException {
    if (!isWriter(Thread.currentThread())) {
      throw new IllegalMonitorStateException("Calling Thread does not hold the write lock on this ReadWriteLock");
    }
    writeAccesses--;
    if (writeAccesses == 0) {
      writingThread = null;
      // Notify only when the last write lock is released.
      notifyAll();
    }
  }

  private boolean canGrantWriteAccess(Thread callingThread) {
    if (isOnlyReader(callingThread)) {
      return true;
    }
    if (hasReaders()) {
      return false;
    }
    if (writingThread == null) {
      return true;
    }
    if (!isWriter(callingThread)) {
      return false;
    }
    return true;
  }


  private int getReadAccessCount(Thread callingThread) {
    Integer accessCount = readingThreads.get(callingThread);
    if (accessCount == null) {
      return 0;
    }
    return accessCount;
  }


  private boolean hasReaders() {
    return readingThreads.size() > 0;
  }

  private boolean isReader(Thread callingThread) {
    return readingThreads.get(callingThread) != null;
  }

  private boolean isOnlyReader(Thread callingThread) {
    return readingThreads.size() == 1 &&
        readingThreads.get(callingThread) != null;
  }

  private boolean hasWriter() {
    return writingThread != null;
  }

  private boolean isWriter(Thread callingThread) {
    return writingThread == callingThread;
  }

  private boolean hasWriteRequests() {
    return this.writeRequests > 0;
  }
}
