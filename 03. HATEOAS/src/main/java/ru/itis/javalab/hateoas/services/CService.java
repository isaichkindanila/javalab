package ru.itis.javalab.hateoas.services;

import ru.itis.javalab.hateoas.models.C;

public interface CService {
    C update(Long id, C.Value value);
}
