package com.learn.learnboot;

import com.learn.learnboot.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LearnbootApplicationTests {

    @Autowired
    Person p;
    @Test
    void contextLoads() {
        System.out.println(p.toString());
    }

}
