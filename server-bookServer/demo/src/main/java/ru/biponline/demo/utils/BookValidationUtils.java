package ru.biponline.demo.utils;

import ru.biponline.demo.entity.BookEntity;
import ru.biponline.demo.exception.ValidationExceptionBook;

public class BookValidationUtils {
    public static void bookValidationUtils(BookEntity book){
        String title = book.getTitle();
        if (title == null || title.isBlank()){
            throw new ValidationExceptionBook("Поле название не может быть пустым!");
        }
        if (book.getAuthor() == null){
            throw new ValidationExceptionBook("Нужно выбрать автора!");
        }
        if (book.getPublishing() == null){
            throw new ValidationExceptionBook("Нужно выбрать издательство!");
        }
    }
}
