package ru.itis.javalab.query.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("e")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class E {
    @Id
    private String id;
    @DBRef
    private A a;
    @DBRef
    private B b;
}
