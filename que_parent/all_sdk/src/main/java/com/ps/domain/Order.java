package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class Order implements Serializable {
    private int id;
    private int userId;
    private int shopId;
    private String time;
}
