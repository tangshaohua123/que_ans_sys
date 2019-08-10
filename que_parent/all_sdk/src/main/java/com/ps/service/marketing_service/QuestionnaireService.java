package com.ps.service.marketing_service;

import com.ps.domain.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface QuestionnaireService {
    void addQunaire(List<QNaire> qNaire, Question question);

    Question getTitle(int id);

    List<QNaire> getQue(int id);

    void answer(List<QuResult> quResult);

    String invitation(int id);

    Result seckill(Order order) throws ParseException;
}
