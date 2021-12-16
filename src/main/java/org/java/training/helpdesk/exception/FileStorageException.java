package org.java.training.helpdesk.exception;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String exceptionText) {
        super(exceptionText);
    }
}