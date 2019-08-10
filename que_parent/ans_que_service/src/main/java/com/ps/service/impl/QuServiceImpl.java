package com.ps.service.impl;

import com.ps.domain.Answer;
import com.ps.domain.Issue;
import com.ps.mapper.QuMapper;
import com.ps.service.qu_sevice.QuService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@Service(version = "1.0.0")
public class QuServiceImpl implements QuService {

    @Autowired
    private QuMapper quMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public List<Issue> queryAll() {
        return quMapper.queryAll();
    }

    @Override
    public List<Answer> queryQueById(int id) {
        return quMapper.queryQueById(id);
    }

    @Override
    public void addAns(Issue issue) {
        quMapper.addAns(issue);
    }

    @Override
    public void answer(Answer answer) {
        quMapper.answer(answer);
    }

    @Override
    public void adopt(Answer answer) {
        quMapper.adopt(answer);
        kafkaTemplate.send("ps","foo" ,System.currentTimeMillis()+"-"+answer.getUserId());
    }

    @Override
    public int queryIntegral(Answer answer) {
        return quMapper.queryIntegral(answer);
    }
}
