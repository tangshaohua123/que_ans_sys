package com.ps.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class QuResult implements Serializable {
    private int id;
    private int detailId;
    private int userId;
    private String answer;
}
