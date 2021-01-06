package ru.itis.javalab.tsk.repositories;

import ru.itis.javalab.tsk.models.LastIteration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastIterationRepository extends JpaRepository<LastIteration, Integer> {
}
