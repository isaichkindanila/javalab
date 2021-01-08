package ru.itis.javalab.query.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.itis.javalab.query.models.E;

public interface ERepo extends MongoRepository<E, String>, QuerydslPredicateExecutor<E> {
}
