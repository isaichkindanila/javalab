package ru.itis.javalab.tsk.controllers;

import ru.itis.javalab.tsk.dto.DocDto;
import ru.itis.javalab.tsk.services.interfaces.DocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/docs")
class DocController {

    private final DocService docService;

    @PostMapping("")
    public Map<?, ?> create(@RequestBody DocDto dto) {
        return Map.of("id", docService.create(dto));
    }
}
