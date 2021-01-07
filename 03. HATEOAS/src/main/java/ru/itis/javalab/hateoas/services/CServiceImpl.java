package ru.itis.javalab.hateoas.services;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.hateoas.models.C;
import ru.itis.javalab.hateoas.repositories.CRepo;

@Component
@AllArgsConstructor
class CServiceImpl implements CService {

    private final CRepo repo;

    @Override
    public C update(Long id, C.Value value) {
        var c = repo.findById(id).orElseThrow(ResourceNotFoundException::new);
        c.setValue(value);

        return repo.save(c);
    }
}
