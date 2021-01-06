package ru.kpfu.itis.rabbitmq.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Info {
    String firstName;
    String lastName;
    String id;
    int age;
}
