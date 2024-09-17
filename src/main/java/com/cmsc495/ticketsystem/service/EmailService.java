package com.cmsc495.ticketsystem.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${GMAIL_CLIENT_ID}")
    private String clientId;

    @Value("${GMAIL_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${google.refresh-token}")
    private String refreshToken;

    public boolean sendEmail(String toEmail, String subject, String body) throws Exception {
        // Build the GoogleCredential object with your OAuth2 details
        GoogleCredential credential = new GoogleCredential.Builder()
                .setClientSecrets(clientId, clientSecret)
                .setJsonFactory(JacksonFactory.getDefaultInstance())
                .setTransport(GoogleNetHttpTransport.newTrustedTransport())
                .build();

        // Set the refresh token
        credential.setRefreshToken(refreshToken);

        // Refresh the access token using the refresh token
        if (credential.refreshToken()) {
            Gmail service = new Gmail.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
                    .setApplicationName("Gmail API")
                    .build();

            MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
            email.setFrom(new InternetAddress("your-email@gmail.com"));
            email.addRecipient(jakarta.mail.Message.RecipientType.TO, new InternetAddress(toEmail));
            email.setSubject(subject);
            email.setText(body);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            email.writeTo(buffer);
            byte[] rawMessage = buffer.toByteArray();
            String encodedEmail = Base64.getUrlEncoder().encodeToString(rawMessage);

            Message message = new Message();
            message.setRaw(encodedEmail);

            service.users().messages().send("me", message).execute();
        } else {
            throw new Exception("Failed to refresh access token");
        }
        return false;
    }
}
