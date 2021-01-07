package ru.itis.javalab.mongo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("d")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class D {
    @Id
    private String id;
    @DBRef
    private C c;
}
