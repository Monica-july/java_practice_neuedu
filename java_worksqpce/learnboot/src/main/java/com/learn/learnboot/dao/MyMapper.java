package com.learn.learnboot.dao;

import com.learn.learnboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyMapper {
    List<User> queryAll();

    boolean updateUser(User user);
}
