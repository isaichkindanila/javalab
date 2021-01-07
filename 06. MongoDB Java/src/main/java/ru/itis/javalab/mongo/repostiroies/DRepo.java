package ru.itis.javalab.mongo.repostiroies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab.mongo.models.D;

@RepositoryRestResource(path = "d")
public interface DRepo extends MongoRepository<D, String> {
}
