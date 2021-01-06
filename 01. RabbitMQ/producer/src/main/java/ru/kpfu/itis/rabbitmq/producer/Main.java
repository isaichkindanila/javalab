package ru.kpfu.itis.rabbitmq.producer;

public class Main {

    public static void main(String[] args) {
        new RabbitMQProducer(new ConsoleReader(), "localhost").startProducing();
    }
}
