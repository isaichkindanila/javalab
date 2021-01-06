package ru.itis.javalab.queue.chain;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itis.javalab.queue.chain.clients.QueueClient;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class QueueChainApplication {

	@Bean
	public Connection connection() {
		try {
			return new ConnectionFactory().newConnection();
		} catch (IOException | TimeoutException e) {
			throw new IllegalStateException(e);
		}
	}

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(QueueChainApplication.class, args)
				.getBeansOfType(QueueClient.class)
				.values()
				.forEach(QueueClient::start);
	}
}
