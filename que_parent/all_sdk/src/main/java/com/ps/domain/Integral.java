package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Integral implements Serializable {
    private int id;
    private int userId;
    private int addition;
    private String type;
}
