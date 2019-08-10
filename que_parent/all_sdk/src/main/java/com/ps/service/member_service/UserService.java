package com.ps.service.member_service;

import com.ps.domain.Answer;
import com.ps.domain.Integral;
import com.ps.domain.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface UserService {
    int register(User user) throws ParseException;

    List<Integral> queryAllIntegral(int id);

    void addIntegral(Answer answer,int integral,int id);

    void addCode(String code, int id);

    User queryById(int userId);
}
