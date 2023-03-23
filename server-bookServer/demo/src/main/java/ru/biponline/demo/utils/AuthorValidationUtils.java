package ru.biponline.demo.utils;

import ru.biponline.demo.entity.AuthorEntity;
import ru.biponline.demo.exception.ValidationExceptionAuthor;

public class AuthorValidationUtils {
    public static void authorValidation(AuthorEntity data) throws ValidationExceptionAuthor {
        String name = data.getName();
        if (name == null || name.isBlank()){
            throw new ValidationExceptionAuthor("У Автора должно быть имя!");
        }
        String surname = data.getSurname();
        if (surname == null || surname.isBlank()){
            throw new ValidationExceptionAuthor("У Автора должна быть фамилия");
        }
    }
}
