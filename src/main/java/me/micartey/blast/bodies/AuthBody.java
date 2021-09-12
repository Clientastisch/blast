package me.micartey.blast.bodies;

import lombok.Data;

@Data
public class AuthBody {
    private final String mail, password;
}
