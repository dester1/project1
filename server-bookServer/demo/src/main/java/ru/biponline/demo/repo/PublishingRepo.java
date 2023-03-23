package ru.biponline.demo.repo;

import org.springframework.data.repository.CrudRepository;
import ru.biponline.demo.entity.PublishingEntity;

public interface PublishingRepo extends CrudRepository<PublishingEntity, Long> {
}