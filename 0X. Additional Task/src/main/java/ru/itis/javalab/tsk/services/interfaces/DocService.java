package ru.itis.javalab.tsk.services.interfaces;

import ru.itis.javalab.tsk.dto.DocDto;
import ru.itis.javalab.tsk.models.Doc;

public interface DocService {
    int create(DocDto dto);
    void failDeadline(Doc doc);
}
