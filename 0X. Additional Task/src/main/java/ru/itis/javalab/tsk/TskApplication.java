package ru.itis.javalab.tsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TskApplication {

    public static void main(String[] args) {
        SpringApplication.run(TskApplication.class, args);
    }
}
