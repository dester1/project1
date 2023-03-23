package ru.biponline.demo.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.biponline.demo.entity.PublishingEntity;

import java.util.Optional;

@Data
@EqualsAndHashCode(callSuper = false)
public class PublishingResponse extends BaseResponse {
    public PublishingResponse(boolean success, String message, PublishingEntity publishing){
        super(success,message);
        this.publishing=publishing;
    }
    public PublishingResponse(PublishingEntity publishing){
        super(true,"Publishing Data");
        this.publishing=publishing;
    }

    public PublishingResponse(Optional<PublishingEntity> publishing1){
        super(true,"Publishing Data");
        this.publishing1=publishing1;
    }
    private PublishingEntity publishing;
    private Optional<PublishingEntity> publishing1;

}
