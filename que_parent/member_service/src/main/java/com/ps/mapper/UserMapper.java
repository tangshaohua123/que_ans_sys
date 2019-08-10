package com.ps.mapper;

import com.ps.domain.Integral;
import com.ps.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    void register(User user);

    List<Integral> queryAllIntegral(int id);

    void addCode(String code, int id);

    User queryById(int userId);
}
