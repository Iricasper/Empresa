package com.empresa.exceptions;

public class DatosNoCorrectosException extends RuntimeException {
    public DatosNoCorrectosException(String message) {
        super(message);
    }
}
