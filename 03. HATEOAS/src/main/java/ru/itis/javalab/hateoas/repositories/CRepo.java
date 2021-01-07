package ru.itis.javalab.hateoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab.hateoas.models.C;

@RepositoryRestResource(path = "c")
public interface CRepo extends JpaRepository<C, Long> {

    @Query("from C c where c.value = 'QWE'")
    @RestResource(path = "qwe", rel = "qwe")
    Page<C> findAllQwe(Pageable request);

    @Query("from C c where c.value = 'ASD'")
    @RestResource(path = "asd", rel = "asd")
    Page<C> findAllAsd(Pageable request);
}
