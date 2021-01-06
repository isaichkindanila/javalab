package ru.itis.javalab.queue.chain.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.queue.chain.dto.HtmlToPdfDto;
import ru.itis.javalab.queue.chain.dto.RequestDto;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;
import ru.itis.javalab.queue.chain.services.interfaces.TemplateService;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class RefClient implements QueueClient {

    private final MQService mqService;
    private final TemplateService templateService;

    @Value("${qc.queue.req.ref}")
    private String queue;

    @Value("${qc.exchange.convert}")
    private String exchange;

    @Override
    public void start() {
        mqService.subscribe(RequestDto.class, queue, (data, key) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("data", data);

            String html = templateService.process("ref", model);
            HtmlToPdfDto dto = new HtmlToPdfDto(html, data.getEmail());

            mqService.send(exchange, "", dto);
        });
    }
}
