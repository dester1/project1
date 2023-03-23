package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.BookEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookListResponse extends BaseResponse {
    public BookListResponse(Iterable<BookEntity> data) {
        super(true, "Books");
        this.data=data;
    }
    private Iterable<BookEntity> data;
}
