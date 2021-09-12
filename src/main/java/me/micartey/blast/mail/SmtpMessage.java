package me.micartey.blast.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SmtpMessage {

    private final Message message;

    public SmtpMessage(Session session, String mail, String recipient, String subject, String text) throws MessagingException {
        this.message = new MimeMessage(session);
        this.message.setFrom(new InternetAddress(mail));
        this.message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(recipient)
        );
        this.message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(text, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
    }

    public void send() throws MessagingException {
        Transport.send(message);
    }
}
