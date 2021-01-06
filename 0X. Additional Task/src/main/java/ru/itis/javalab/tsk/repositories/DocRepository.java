package ru.itis.javalab.tsk.repositories;

import ru.itis.javalab.tsk.models.Doc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.ZonedDateTime;
import java.util.stream.Stream;

public interface DocRepository extends JpaRepository<Doc, Integer> {
    Stream<Doc> findAllByDeadlineBetween(ZonedDateTime after, ZonedDateTime before);
}
