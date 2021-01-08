package ru.itis.javalab.query.controllers;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.query.models.E;
import ru.itis.javalab.query.repos.ERepo;

@RestController
@RequestMapping("/e")
@AllArgsConstructor
public class EController {

    private final ERepo repo;

    @GetMapping
    public Iterable<E> query(@QuerydslPredicate Predicate predicate) {
        return repo.findAll(predicate);
    }
}
