package ru.itis.javalab.tsk.services.impl;

import ru.itis.javalab.tsk.models.LastIteration;
import ru.itis.javalab.tsk.repositories.LastIterationRepository;
import ru.itis.javalab.tsk.services.interfaces.LastIterationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
class LastIterationServiceImpl implements LastIterationService {

    private final LastIterationRepository repo;

    private LastIteration get() {
        return repo.findById(0).orElseThrow(
                () -> new IllegalStateException("last iteration timestamp is missing - something is wrong with database")
        );
    }

    @Override
    public ZonedDateTime getTimestamp() {
        return get().getTimestamp();
    }

    @Override
    public void setLastIteration(ZonedDateTime timestamp) {
        var iter = get();
        iter.setTimestamp(timestamp);
        repo.save(iter);
    }
}
