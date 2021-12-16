package org.java.training.helpdesk.entity.enums;

import java.util.Optional;

public enum Role {
    EMPLOYEE("EMPLOYEE"),
    ADMIN("ADMIN");

    private String action;

    Role(String action) {
        this.action = action;
    }

    public static Optional<Role> fromAction(String action) {
        Optional<Role> newRole = Optional.empty();
        for (Role role : values()) {
            if (role.getAction().equals(action)) {
                newRole = Optional.of(role);
            }
        }
        return newRole;
    }

    public String getAction() {
        return action;
    }
}