package ru.biponline.demo.exception;

import javax.validation.ValidationException;

public class ValidationExceptionBook extends ValidationException {
    public ValidationExceptionBook(String message){
        super(message);
    }
}