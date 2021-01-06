package ru.kpfu.itis.rabbitmq.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class RabbitMQProducer {
    private static final String EXCHANGE_NAME = "asdf";
    private static final BuiltinExchangeType EXCHANGE_TYPE = BuiltinExchangeType.FANOUT;

    private final Supplier<Info> infoSupplier;

    private final ConnectionFactory factory = new ConnectionFactory();
    private final ObjectMapper mapper = new ObjectMapper();

    public RabbitMQProducer(Supplier<Info> infoSupplier, String host) {
        this.infoSupplier = infoSupplier;
        factory.setHost(host);
    }

    public void startProducing() {
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

            while (true) {
                Info info = infoSupplier.get();
                String infoJson = mapper.writeValueAsString(info);

                channel.basicPublish(EXCHANGE_NAME, "", null, infoJson.getBytes());
                System.out.println("sent info to exchange");
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
