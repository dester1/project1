package ru.biponline.demo.repo;

import org.springframework.data.repository.CrudRepository;
import ru.biponline.demo.entity.AuthorEntity;

public interface AuthorRepo extends CrudRepository<AuthorEntity, Long> {
}