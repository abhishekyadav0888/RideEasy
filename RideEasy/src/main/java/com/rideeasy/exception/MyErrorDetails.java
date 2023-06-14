package com.rideeasy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

}
