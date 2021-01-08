package ru.itis.javalab.query.controllers;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.query.models.B;
import ru.itis.javalab.query.repos.BRepo;

@RestController
@RequestMapping("/b")
@AllArgsConstructor
public class BController {

    private final BRepo repo;

    @GetMapping
    public Iterable<B> query(@QuerydslPredicate Predicate predicate) {
        return repo.findAll(predicate);
    }
}
