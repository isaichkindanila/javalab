package ru.itis.javalab.mongo.crud;

import java.util.Optional;

public interface CrRepo<T, ID> {
    Optional<T> read(ID id);
    ID create(T value);
}
