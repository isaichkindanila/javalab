package ru.itis.javalab.queue.chain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Value
public class RequestDto {
    Type type;
    Sex sex;
    int height;
    int weight;
    String email;

    public enum Type {
        @JsonProperty("doc")
        DOC,
        @JsonProperty("ref")
        REF
    }

    public enum Sex {
        @JsonProperty("male")
        MALE,
        @JsonProperty("female")
        FEMALE
    }
}
