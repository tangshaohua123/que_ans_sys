package com.ps.mapper;

import com.ps.domain.Answer;
import com.ps.domain.Issue;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuMapper {
    List<Issue> queryAll();

    List<Answer> queryQueById(int id);

    void addAns(Issue issue);

    void answer(Answer answer);

    void adopt(Answer answer);

    int queryIntegral(Answer answer);
}
