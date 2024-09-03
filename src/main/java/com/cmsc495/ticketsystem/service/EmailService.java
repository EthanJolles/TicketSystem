package com.cmsc495.ticketsystem.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${MAIL_ADDRESS}")
    private String fromAddress;

    public void sendEmail( String to, String subject, String text, String accessToken) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setText(text, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(fromAddress);

        mimeMessage.addHeader("Authorization", "Bearer" + accessToken);

        javaMailSender.send(mimeMessage);
    }
}
