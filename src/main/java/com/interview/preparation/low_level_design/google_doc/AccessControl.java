package com.interview.preparation.low_level_design.google_doc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccessControl {
  private final Map<String, Permission> userPermissions;

  public AccessControl() {
    this.userPermissions =  new ConcurrentHashMap<>();
  }

  public void grantPermission(User user, Permission permission) {
    userPermissions.put(user.getId(), permission);
  }

  public Permission getPermission(User user) {
    return userPermissions.getOrDefault(user.getId(), Permission.VIEW);
  }

  public boolean canEdit(User user) {
    return getPermission(user) == Permission.EDIT;
  }
}
