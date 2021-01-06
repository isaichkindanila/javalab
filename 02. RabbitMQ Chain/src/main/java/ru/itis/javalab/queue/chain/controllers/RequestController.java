package ru.itis.javalab.queue.chain.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.queue.chain.dto.RequestDto;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

@RestController
@RequiredArgsConstructor
class RequestController {

    private final MQService mqService;

    @Value("${qc.exchange.req}")
    private String exchange;

    @PostMapping(value = "/api", consumes = "application/json")
    public void doRequest(@RequestBody RequestDto dto) {
        String key = "";
        switch (dto.getType()) {
            case DOC:
                key = "doc";
                break;
            case REF:
                key = "ref";
                break;
        }

        key += ".";

        switch (dto.getSex()) {
            case MALE:
                key += "m";
                break;
            case FEMALE:
                key += "f";
                break;
        }

        mqService.send(exchange, key, dto);
    }
}
