package com.example.exception;

public class TestException extends RuntimeException {
    private static final long serialVersionUID = -8906689019683890917L;

    public TestException() { super(); }

    public TestException(String message) { super(message); }

    public TestException(String message, Throwable cause) { super(message, cause); }

    public TestException(Throwable cause) { super(cause); }
}
