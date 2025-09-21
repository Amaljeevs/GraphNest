package com.antspace.GraphNest.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ServerResponse<T> {
    private int code;
    private String message;
    private T details;
}
