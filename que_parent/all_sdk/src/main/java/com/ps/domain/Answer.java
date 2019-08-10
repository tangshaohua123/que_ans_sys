package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Answer implements Serializable {
    private int id;
    private int issueId;
    private String answer;
    private int userId;
}
