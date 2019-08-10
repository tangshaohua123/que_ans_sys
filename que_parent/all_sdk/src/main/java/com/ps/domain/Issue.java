package com.ps.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Issue implements Serializable {
    private int id;
    private String description;
    private String answerId;
    private int integral;
    private int userId;
}
