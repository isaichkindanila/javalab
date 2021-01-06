package ru.kpfu.itis.rabbitmq.consumer;

public class Main {

    private static void doConsume(String queue, String title) {
        PdfGenerator generator = new PdfGenerator("./pdf/" + queue, title);
        new RabbitMQConsumer(generator::generatePdf, queue, "localhost").startConsuming();
    }

    public static void main(String[] args) {
        doConsume("qwerty006", "PDF номер раз");
        doConsume("qwerty007", "PDF номер два-с");
        doConsume("qwerty008", "PDF номер три-с");
    }
}
