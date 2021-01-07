package ru.itis.javalab.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.itis.javalab.hateoas.models.A;

@RepositoryRestResource(path = "a")
public interface ARepo extends JpaRepository<A, Long> {
}
