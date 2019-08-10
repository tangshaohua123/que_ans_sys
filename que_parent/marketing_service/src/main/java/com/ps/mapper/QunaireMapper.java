package com.ps.mapper;

import com.ps.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QunaireMapper {
    void addTitle(Question question);

    int queryTitle(Question question);

    void addQunaire(List<QNaire> qNaire);

    Question getTitle(int id);

    List<QNaire> getQue(int id);

    void answer(List<QuResult> quResult);

    void addInte(int i);

    void flow(int i, int i1);

    Shop queryShop(int shopId);

    Order getOrder(int userId, int shopId);
}
