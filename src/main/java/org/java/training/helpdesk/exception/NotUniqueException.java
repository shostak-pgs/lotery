package org.java.training.helpdesk.exception;

public class NotUniqueException extends RuntimeException {
    public NotUniqueException() {
        super("The email must to be unique");
    }
}