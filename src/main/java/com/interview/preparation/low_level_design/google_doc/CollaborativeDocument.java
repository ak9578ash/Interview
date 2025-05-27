package com.interview.preparation.low_level_design.google_doc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CollaborativeDocument {
  private String content;
  private final String documentId;
  private final String documentName;
  private final AccessControl accessControl;
  private final List<DocumentVersion> versionHistory;
  private final Map<String, Cursor> userCursors;
  private final Set<User> activeUsers = ConcurrentHashMap.newKeySet();

  public CollaborativeDocument(String documentName, String initialContent) {
    this.documentId = UUID.randomUUID().toString();
    this.documentName = documentName;
    this.content = initialContent;
    this.accessControl = new AccessControl();
    this.versionHistory = new ArrayList<>();
    this.userCursors = new ConcurrentHashMap<>();
    saveVersion(null); // Initial version
  }

  public synchronized void editDocument(User user, String newContent) {
    if (!accessControl.canEdit(user)) {
      throw new SecurityException("User does not have edit permissions.");
    }
    this.content = newContent;
    saveVersion(user.getId());
    broadcastChanges();
  }

  public synchronized void updateCursor(User user, int position) {
    userCursors.put(user.getId(), new Cursor(position));
    broadcastCursorUpdate(user);
  }

  public synchronized void joinDocument(User user) {
    activeUsers.add(user);
    userCursors.put(user.getId(), new Cursor(0));
    broadcastPresence();
  }

  public synchronized void leaveDocument(User user) {
    activeUsers.remove(user);
    userCursors.remove(user.getId());
    broadcastPresence();
  }

  private void saveVersion(String userId) {
    versionHistory.add(new DocumentVersion(content, userId));
  }

  public synchronized void revertToVersion(int versionIndex) {
    if (versionIndex >= 0 && versionIndex < versionHistory.size()) {
      this.content = versionHistory.get(versionIndex).getContent();
      broadcastChanges();
    } else {
      throw new IllegalArgumentException("Invalid version index.");
    }
  }

  public List<DocumentVersion> getVersionHistory() {
    return Collections.unmodifiableList(versionHistory);
  }

  public void shareWith(User user, Permission permission) {
    accessControl.grantPermission(user, permission);
  }

  private void broadcastChanges() {
    // Simulate real-time broadcasting to clients
    log.info("Broadcasting updated content to all active users.");
  }

  private void broadcastCursorUpdate(User user) {
    // Simulate real-time cursor update
    log.info("Broadcasting cursor position of user: {}", user.getName());
  }

  private void broadcastPresence() {
    log.info("Current active users: ");
    activeUsers.forEach(u -> log.info("- " + u.getName()));
  }
}
