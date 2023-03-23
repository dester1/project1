package ru.biponline.demo.repo;

import org.springframework.data.repository.CrudRepository;
import ru.biponline.demo.entity.BookEntity;

public interface BookRepo extends CrudRepository<BookEntity, Long> {
    Iterable<BookEntity> findDistinctByTitleOrPublishingCity(String title, String city);
}
