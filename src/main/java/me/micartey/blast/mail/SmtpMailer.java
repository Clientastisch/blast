package me.micartey.blast.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SmtpMailer {

    private final Properties properties;

    public SmtpMailer(String host, int port, boolean starttls) {
        this.properties = new Properties();
        this.properties.put("mail.smtp.auth", "true");
        this.properties.put("mail.smtp.starttls.enable", starttls);
        this.properties.put("mail.smtp.host", host);
        this.properties.put("mail.smtp.port", String.valueOf(port));
    }

    public Session createSession(String mail, String password) {
        return Session.getInstance(this.properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        mail,
                        password
                );
            }
        });
    }
}
