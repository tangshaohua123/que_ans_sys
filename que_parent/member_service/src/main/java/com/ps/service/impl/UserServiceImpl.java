package com.ps.service.impl;

import com.ps.domain.Answer;
import com.ps.domain.Integral;
import com.ps.domain.User;
import com.ps.mapper.UserMapper;
import com.ps.service.member_service.UserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public int register(User user) {
        userMapper.register(user);
        kafkaTemplate.send("panshi","foo" ,System.currentTimeMillis()+"");
        return 0;
    }

    @Override
    public List<Integral> queryAllIntegral(int id) {
        return userMapper.queryAllIntegral(id);
    }

    @Override
    public void addIntegral(Answer answer,int integral,int id) {

    }

    @Override
    public void addCode(String code, int id) {
        userMapper.addCode(code,id);
    }

    @Override
    public User queryById(int userId) {
        return userMapper.queryById(userId);
    }
}
