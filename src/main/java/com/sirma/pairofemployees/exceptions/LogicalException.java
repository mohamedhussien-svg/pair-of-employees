package com.sirma.pairofemployees.exceptions;

public class LogicalException extends Exception {
    private String message;

    public LogicalException(String message) {
        super(message);
    }
}
