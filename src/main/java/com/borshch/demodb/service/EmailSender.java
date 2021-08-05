package com.borshch.demodb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSender {


    private final JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text)

    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("alexohuennyy@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void newCustomerMessage (String to) {
        sendSimpleMessage(to, "Hello!", "Hello, Congr-s");
    }

}
