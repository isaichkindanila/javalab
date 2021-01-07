package ru.itis.javalab.mongo.crud;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import ru.itis.javalab.mongo.models.A;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CrRepoTemplateImpl implements CrRepo<A, String> {

    private final MongoTemplate template;

    @Override
    public Optional<A> read(String s) {
        return Optional.ofNullable(template.findById(s, A.class));
    }

    @Override
    public String create(A a) {
        return template.insert(a).getId();
    }
}
