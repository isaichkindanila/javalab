package ru.itis.javalab.mongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("c")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class C {
    @Id
    private String id;
    private Value value;

    public enum Value {
        QWE, ASD
    }
}