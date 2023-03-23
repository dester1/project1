package ru.biponline.demo.exception;

import javax.validation.ValidationException;

public class ValidationExceptionPublishing extends ValidationException {
    public ValidationExceptionPublishing(String message){
        super(message);
    }
}
