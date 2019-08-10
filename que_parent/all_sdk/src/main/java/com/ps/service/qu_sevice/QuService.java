package com.ps.service.qu_sevice;

import com.ps.domain.Answer;
import com.ps.domain.Issue;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

@Service
public interface QuService {
    List<Issue> queryAll();

    List<Answer> queryQueById(int id);

    void addAns(Issue issue);

    void answer(Answer issue);

    void adopt(Answer answer);

    int queryIntegral(Answer answer);
}
