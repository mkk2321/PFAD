package com.example.pd.exceptions;

public class InvalidClientException extends Exception{
    public InvalidClientException() {
    }

    public InvalidClientException(String message) {
        super(message);
    }
}
