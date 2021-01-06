package ru.itis.javalab.queue.chain.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.queue.chain.dto.RequestDto;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

@Component
@RequiredArgsConstructor
public class FemaleStatClient implements QueueClient {

    private final MQService mqService;

    @Value("${qc.queue.stat.f}")
    private String queue;

    @Override
    public void start() {
        mqService.subscribe(RequestDto.class, queue, (data, key) -> {
            System.out.println("Weight of " + data.getEmail() + " is " + data.getWeight() + " kg");
        });
    }
}
