package me.micartey.blast.bodies;

import lombok.Data;

@Data
public class MailBody {
    private final String      host;
    private final int         port;
    private final boolean     starttls;
    private final AuthBody    auth;
    private final MessageBody message;
}
