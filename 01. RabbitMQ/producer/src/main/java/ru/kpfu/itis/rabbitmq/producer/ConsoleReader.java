package ru.kpfu.itis.rabbitmq.producer;

import java.util.Scanner;
import java.util.function.Supplier;

public class ConsoleReader implements Supplier<Info> {

    @Override
    public Info get() {
        Scanner scanner = new Scanner(System.in);

        return Info.builder()
                .firstName(scanner.nextLine())
                .lastName(scanner.nextLine())
                .id(scanner.nextLine())
                .age(scanner.nextInt())
                .build();
    }
}
