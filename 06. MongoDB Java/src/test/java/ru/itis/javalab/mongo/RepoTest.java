package ru.itis.javalab.mongo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itis.javalab.mongo.crud.CrRepo;
import ru.itis.javalab.mongo.crud.CrRepoDriverImpl;
import ru.itis.javalab.mongo.crud.CrRepoTemplateImpl;
import ru.itis.javalab.mongo.models.A;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RepoTest {

    @Autowired
    private CrRepoDriverImpl repoDriver;

    @Autowired
    private CrRepoTemplateImpl repoTemplate;

    private void testRepo(CrRepo<A, String> repo) {
        var value = new Random().nextDouble();
        var id = repo.create(A.builder().value(value).build());
        var optional = repo.read(id);

        assertTrue(optional.isPresent());
        assertEquals(value, optional.get().getValue());
    }

    @Test
    public void testDriverRepo() {
        testRepo(repoDriver);
    }

    @Test
    public void testTemplateRepo() {
        testRepo(repoTemplate);
    }
}
