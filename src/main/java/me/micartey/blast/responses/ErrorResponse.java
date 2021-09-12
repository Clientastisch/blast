package me.micartey.blast.responses;

import lombok.Data;

@Data
public class ErrorResponse implements Response {
    private final String error;
}
