package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class User implements Serializable {
    private int id;
    private String name;
    private String phone;
    private String password;
    private int integral;
    private String code;
}
