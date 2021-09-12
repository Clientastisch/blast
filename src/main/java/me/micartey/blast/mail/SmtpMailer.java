package me.micartey.blast.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Objects;
import java.util.Properties;

public class SmtpMailer {

    private final Properties properties;

    private PasswordAuthentication authentication;
    private Session                session;

    public SmtpMailer(String host, int port, boolean starttls) {
        this.properties = new Properties();
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", starttls);
        this.properties.put("mail.smtp.host", host);
        this.properties.put("mail.smtp.port", String.valueOf(port));
    }

    public void createSession(String mail, String password) {
        this.authentication = new PasswordAuthentication(
                mail,
                password
        );

        this.session = Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return authentication;
            }
        });
    }

    public void sendMessage(String recipient, String subject, String text) throws MessagingException {
        Objects.requireNonNull(this.authentication);
        Objects.requireNonNull(this.session);

        Message message = new MimeMessage(this.session);
        message.setFrom(new InternetAddress(this.authentication.getUserName()));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipient)
        );
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(text, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
        Transport.send(message);
    }
}
