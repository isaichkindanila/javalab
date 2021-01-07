package ru.itis.javalab.mongo.repostiroies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab.mongo.models.C;

@RepositoryRestResource(path = "c")
public interface CRepo extends MongoRepository<C, String> {
    @RestResource(path = "qwe", rel = "qwe")
    @Query("{value: 'QWE'}")
    Page<C> findAllQWE(Pageable request);

    @RestResource(path = "asd", rel = "asd")
    @Query("{value: 'ASD'}")
    Page<C> findAllASD(Pageable request);
}
