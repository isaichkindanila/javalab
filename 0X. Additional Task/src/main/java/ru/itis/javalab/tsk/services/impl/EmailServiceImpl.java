package ru.itis.javalab.tsk.services.impl;

import ru.itis.javalab.tsk.services.interfaces.EmailService;
import org.springframework.stereotype.Service;

@Service
class EmailServiceImpl implements EmailService {

    @Override
    public void sendMessage(String email, String template, Object data) {
        System.out.println("sending message to " + email);
    }
}
