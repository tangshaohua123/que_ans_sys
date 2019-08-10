package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Question implements Serializable {
    private int id;
    private String title;
    private int integral;
}
