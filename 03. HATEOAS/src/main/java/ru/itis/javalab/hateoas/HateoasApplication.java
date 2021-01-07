package ru.itis.javalab.hateoas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.javalab.hateoas.models.*;
import ru.itis.javalab.hateoas.repositories.*;

import java.util.List;

@SpringBootApplication
public class HateoasApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(HateoasApplication.class, args);

        var as = List.of(
                A.builder().value(1.23).build(),
                A.builder().value(4.56).build()
        );
        var bs = List.of(
                B.builder().value("буп").build(),
                B.builder().value("боп").build()
        );
        var cs = List.of(
                C.builder().value(C.Value.QWE).build(),
                C.builder().value(C.Value.ASD).build()
        );
        var ds = List.of(
                D.builder().c(cs.get(0)).build(),
                D.builder().c(cs.get(1)).build()
        );
        var es = List.of(
                E.builder().a(as.get(0)).b(bs.get(0)).build(),
                E.builder().a(as.get(1)).b(bs.get(1)).build()
        );

        context.getBean(ARepo.class).saveAll(as);
        context.getBean(BRepo.class).saveAll(bs);
        context.getBean(CRepo.class).saveAll(cs);
        context.getBean(DRepo.class).saveAll(ds);
        context.getBean(ERepo.class).saveAll(es);
    }
}
