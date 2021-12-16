package org.java.training.helpdesk.exception;

import org.java.training.helpdesk.entity.User;

public class NotOwnerException extends RuntimeException {
    public NotOwnerException(User user) {
        super(String.format("Only %s permit to modify this article", user.getFirstName()));
    }
}