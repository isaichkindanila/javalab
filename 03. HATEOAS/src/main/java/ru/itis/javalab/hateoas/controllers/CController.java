package ru.itis.javalab.hateoas.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.hateoas.models.C;
import ru.itis.javalab.hateoas.services.CService;

@RepositoryRestController
@AllArgsConstructor
@RequestMapping("/c")
public class CController {

    private final CService service;

    @PutMapping("/{id}/makeQWE")
    public ResponseEntity<?> makeQWE(@PathVariable Long id) {
        return ResponseEntity.ok(EntityModel.of(service.update(id, C.Value.QWE)));
    }

    @PutMapping("/{id}/makeASD")
    public ResponseEntity<?> makeASD(@PathVariable Long id) {
        return ResponseEntity.ok(EntityModel.of(service.update(id, C.Value.ASD)));
    }
}
