package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.PublishingEntity;

@Data
@EqualsAndHashCode(callSuper = false)
public class PublishingListResponse extends BaseResponse {
    public PublishingListResponse(Iterable<PublishingEntity> data){
        super(true,"Publishers");
        this.data=data;
    }

    private Iterable<PublishingEntity> data;
}
