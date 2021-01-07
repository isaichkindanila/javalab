package ru.itis.javalab.mongo.repostiroies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab.mongo.models.B;

@RepositoryRestResource(path = "b")
public interface BRepo extends MongoRepository<B, String> {
}
