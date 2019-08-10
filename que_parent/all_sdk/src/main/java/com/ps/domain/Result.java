package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Result<T> {
    private int code;
    private T body;
    private String message;
}
