package ru.kpfu.itis.rabbitmq.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Info {
    private String firstName;
    private String lastName;
    private String id;
    private int age;
}
