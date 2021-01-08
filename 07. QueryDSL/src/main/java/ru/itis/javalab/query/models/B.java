package ru.itis.javalab.query.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("b")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class B {
    @Id
    private String id;
    private String value;
}
