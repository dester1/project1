package ru.biponline.demo.service;

import org.springframework.stereotype.Service;
import ru.biponline.demo.entity.AuthorEntity;
import ru.biponline.demo.exception.ValidationException;
import ru.biponline.demo.repo.AuthorRepo;
import ru.biponline.demo.utils.AuthorValidationUtils;

@Service
public class AuthorService {
    private final AuthorRepo repo;
    public AuthorService(AuthorRepo repo) {
        this.repo=repo;
    }
    public void save(AuthorEntity author) throws ValidationException {
        AuthorValidationUtils.authorValidation(author);
        repo.save(author);
    }
    public void delete(Long id){repo.deleteById(id);}
    public Iterable<AuthorEntity> getAll(){return repo.findAll();}

}
