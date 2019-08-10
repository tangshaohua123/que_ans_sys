package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Shop implements Serializable {
    private int id;
    private String name;
    private int number;
    private int integral;
    private double money;
    private String start;
    private String end;
}
