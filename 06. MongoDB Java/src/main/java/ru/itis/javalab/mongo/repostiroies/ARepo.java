package ru.itis.javalab.mongo.repostiroies;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab.mongo.models.A;

@RepositoryRestResource(path = "a")
public interface ARepo extends MongoRepository<A, String> {
}
