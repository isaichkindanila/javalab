package ru.itis.javalab.mongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("a")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class A {
    @Id
    private String id;
    private double value;
}
