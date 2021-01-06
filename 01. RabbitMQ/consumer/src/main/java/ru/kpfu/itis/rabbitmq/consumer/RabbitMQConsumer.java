package ru.kpfu.itis.rabbitmq.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;

public class RabbitMQConsumer {
    private static final String EXCHANGE_NAME = "asdf";
    private static final BuiltinExchangeType EXCHANGE_TYPE = BuiltinExchangeType.FANOUT;

    private final ObjectMapper mapper = new ObjectMapper();
    private final ConnectionFactory factory = new ConnectionFactory();

    private final Consumer<Info> infoConsumer;
    private final String queueName;

    public RabbitMQConsumer(Consumer<Info> infoConsumer, String queue, String host) {
        this.infoConsumer = infoConsumer;
        this.queueName = queue;

        factory.setHost(host);
    }

    public void startConsuming() {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, EXCHANGE_NAME, "");

            channel.basicConsume(queueName, (tag, message) -> {
                String json = new String(message.getBody());
                System.out.println(queueName + " received message: " + json);

                try {
                    Info info = mapper.readValue(json, Info.class);
                    infoConsumer.accept(info);

                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (Exception e) {
                    System.err.println("error in " + queueName + ": " + e.toString());
                    e.printStackTrace();
                }
            }, tag -> {});
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
