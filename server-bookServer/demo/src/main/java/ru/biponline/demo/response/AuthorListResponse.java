package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.AuthorEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthorListResponse extends BaseResponse {
    public AuthorListResponse(Iterable<AuthorEntity> data){
        super(true,"Authors");
        this.data=data;
    }
    private Iterable<AuthorEntity> data;
}
