package ru.biponline.demo.utils;

import ru.biponline.demo.entity.PublishingEntity;
import ru.biponline.demo.exception.ValidationExceptionPublishing;

public class PublishingValidationUtils {
    public static void validationPublishing(PublishingEntity data) throws ValidationExceptionPublishing {
        String publisher = data.getPublisher();
        if (publisher == null || publisher.isBlank()){
            throw new ValidationExceptionPublishing("Название не должно быть пустым!");
        }
        String city = data.getCity();
        if (city == null || city.isBlank()){
            throw new ValidationExceptionPublishing("Издательство не может быть без адреса!");
        }
    }
}
