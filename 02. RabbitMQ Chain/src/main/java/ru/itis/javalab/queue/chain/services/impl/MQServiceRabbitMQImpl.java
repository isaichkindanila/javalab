package ru.itis.javalab.queue.chain.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.itis.javalab.queue.chain.services.interfaces.MQService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;

@Service
@Scope("prototype")
class MQServiceRabbitMQImpl implements MQService {

    private final Channel channel;
    private final ObjectMapper objectMapper;

    public MQServiceRabbitMQImpl(Connection connection, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        try {
            channel = connection.createChannel();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void send(String exchange, String routingKey, byte[] data) {
        try {
            channel.basicPublish(exchange, routingKey, null, data);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void send(String exchange, String routingKey, String data) {
        send(exchange, routingKey, data.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void send(String exchange, String routingKey, Object json) {
        try {
            send(exchange, routingKey, objectMapper.writeValueAsBytes(json));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("cannot convert to json: " + json, e);
        }
    }

    @Override
    public <T> void subscribe(Class<T> dataClass, String queue, BiConsumer<T, String> handler) {
        try {
            channel.basicConsume(queue, (tag, message) -> {
                T data = objectMapper.readValue(message.getBody(), dataClass);
                handler.accept(data, message.getEnvelope().getRoutingKey());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }, tag -> {});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void subscribe(String queue, BiConsumer<byte[], String> handler) {
        try {
            channel.basicConsume(queue, (tag, message) -> {
                handler.accept(message.getBody(), message.getEnvelope().getRoutingKey());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
            }, tag -> {});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
