package ru.itis.javalab.queue.chain.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

import java.io.*;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailSenderClient implements QueueClient {

    private final MQService mqService;

    @Value("${qc.queue.pdf.email}")
    private String queue;

    @Override
    public void start() {
        mqService.subscribe(queue, (pdfBytes, email) -> {
            String fileName = UUID.randomUUID().toString() + ".pdf";
            System.out.println("Sending PDF to " + email + " (saving to " + fileName + ")");

            File file = new File("pdf", fileName);
            try (OutputStream out = new FileOutputStream(file)) {
                out.write(pdfBytes);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }
}
