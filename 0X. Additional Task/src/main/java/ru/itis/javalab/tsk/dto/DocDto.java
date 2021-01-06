package ru.itis.javalab.tsk.dto;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class DocDto {
    String email;
    ZonedDateTime deadline;
}
