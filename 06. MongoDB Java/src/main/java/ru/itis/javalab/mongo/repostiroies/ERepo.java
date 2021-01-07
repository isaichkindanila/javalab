package ru.itis.javalab.mongo.repostiroies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab.mongo.models.E;

@RepositoryRestResource(path = "e")
public interface ERepo extends MongoRepository<E, String> {
}
