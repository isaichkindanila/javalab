package ru.itis.javalab.query.controllers;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.query.models.A;
import ru.itis.javalab.query.repos.ARepo;

@RestController
@RequestMapping("/a")
@AllArgsConstructor
public class AController {

    private final ARepo repo;

    @GetMapping
    public Iterable<A> query(@QuerydslPredicate Predicate predicate) {
        return repo.findAll(predicate);
    }
}
