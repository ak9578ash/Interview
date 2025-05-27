package com.interview.preparation.low_level_design.google_doc;

public class GoogleDocMain {
  public static void main(String[] args) {
    User alice = new User("1", "Alice");
    User bob = new User("2", "Bob");

    CollaborativeDocument doc = new CollaborativeDocument("doc-1", "Initial content");
    doc.shareWith(alice, Permission.EDIT);
    doc.shareWith(bob, Permission.EDIT);

    doc.joinDocument(alice);
    doc.joinDocument(bob);

    doc.updateCursor(alice, 5);
    doc.editDocument(alice, "Alice's edit.");

    doc.updateCursor(bob, 12);
    doc.editDocument(bob, "Bob's edit.");

    System.out.println("Current Content: " + doc.getContent());

    doc.revertToVersion(0);
    System.out.println("Reverted Content: " + doc.getContent());

    doc.leaveDocument(alice);
    doc.leaveDocument(bob);
  }
}
