package ru.itis.javalab.queue.chain.services.interfaces;

import java.util.function.BiConsumer;

public interface MQService {
    void send(String exchange, String routingKey, byte[] data);
    void send(String exchange, String routingKey, String data);
    void send(String exchange, String routingKey, Object json);

    // handler = (T, routingKey) -> {...}
    <T> void subscribe(Class<T> dataClass, String queue, BiConsumer<T, String> handler);

    // handler = (data, routingKey) -> {..}
    void subscribe(String queue, BiConsumer<byte[], String> handler);
}
