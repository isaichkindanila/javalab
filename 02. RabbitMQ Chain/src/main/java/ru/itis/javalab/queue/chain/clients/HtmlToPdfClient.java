package ru.itis.javalab.queue.chain.clients;

import com.itextpdf.html2pdf.HtmlConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.javalab.queue.chain.dto.HtmlToPdfDto;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

import java.io.ByteArrayOutputStream;

@Component
@RequiredArgsConstructor
public class HtmlToPdfClient implements QueueClient {

    private final MQService mqService;

    @Value("${qc.queue.converter}")
    private String queue;

    @Value("${qc.exchange.pdf}")
    private String exchange;

    @Override
    public void start() {
        mqService.subscribe(HtmlToPdfDto.class, queue, (data, key) -> {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(data.getHtml(), outputStream);

            mqService.send(exchange, data.getEmail(), outputStream.toByteArray());
        });
    }
}
