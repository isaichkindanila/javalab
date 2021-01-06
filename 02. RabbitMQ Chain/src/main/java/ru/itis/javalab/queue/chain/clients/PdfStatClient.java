package ru.itis.javalab.queue.chain.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

@Component
@RequiredArgsConstructor
public class PdfStatClient implements QueueClient {

    private final MQService mqService;

    @Value("${qc.queue.pdf.stat}")
    private String queue;

    @Override
    public void start() {
        mqService.subscribe(queue, (data, email) -> {
            System.out.println("Received " + data.length + " bytes long file for " + email);
        });
    }
}
