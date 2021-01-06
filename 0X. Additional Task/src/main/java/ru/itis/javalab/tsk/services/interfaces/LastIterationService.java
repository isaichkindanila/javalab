package ru.itis.javalab.tsk.services.interfaces;

import java.time.ZonedDateTime;

public interface LastIterationService {
    ZonedDateTime getTimestamp();
    void setLastIteration(ZonedDateTime timestamp);
}
