package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class QNaire implements Serializable {
    private int id;
    private String number;
    private String question;
    private int titleId;
}
