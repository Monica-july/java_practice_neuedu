package com.learn.learnboot.service;

import com.learn.learnboot.dao.MyMapper;
import com.learn.learnboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    private MyMapper mapper;

    public List<User> queryAll(){
        return mapper.queryAll();
    }

    public boolean updateUser(User user){
        return mapper.updateUser(user);
    }
}
