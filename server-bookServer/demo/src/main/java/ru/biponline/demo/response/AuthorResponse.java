package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.AuthorEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorResponse extends BaseResponse {
    public AuthorResponse(boolean success, String message, AuthorEntity author){
        super(success,message);
        this.author=author;
    }
    public AuthorResponse(AuthorEntity author) {
        super(true,"Author Data");
    }
    private AuthorEntity author;
}
