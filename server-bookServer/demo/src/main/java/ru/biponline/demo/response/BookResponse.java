package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.BookEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookResponse extends BaseResponse {
    public BookResponse(boolean success, String message, BookEntity book){
        super(success,message);
        this.book=book;
    }
    public BookResponse(BookEntity publisher){
        super(true,"Book Data");
    }
    private BookEntity book;
}
