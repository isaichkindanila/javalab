package ru.itis.javalab.tsk.services.interfaces;

public interface EmailService {
    void sendMessage(String email, String template, Object data);
}
