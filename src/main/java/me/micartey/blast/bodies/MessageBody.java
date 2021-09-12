package me.micartey.blast.bodies;

import lombok.Data;

@Data
public class MessageBody {
    private final String recipient;
    private final String subject, text;
}
