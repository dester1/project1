package ru.biponline.demo.service;

import org.springframework.stereotype.Service;
import ru.biponline.demo.entity.PublishingEntity;
import ru.biponline.demo.exception.ValidationException;
import ru.biponline.demo.repo.PublishingRepo;
import ru.biponline.demo.utils.PublishingValidationUtils;

import java.util.Optional;

@Service
public class PublishingService {
    private final PublishingRepo repo;
    public PublishingService(PublishingRepo repo) {
        this.repo=repo;
    }
    public void save(PublishingEntity publisher) throws ValidationException {
        PublishingValidationUtils.validationPublishing(publisher);
        repo.save(publisher);
    }
    public void delete(Long id){repo.deleteById(id);}
    public Iterable<PublishingEntity> getAll(){return repo.findAll();}
    public Optional<PublishingEntity> getPublishing(Long id){return repo.findById(id);}
}
