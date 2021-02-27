package com.learn.learnboot.controller;

import com.learn.learnboot.entity.Person;
import com.learn.learnboot.entity.User;
import com.learn.learnboot.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("my")
public class MyController {
    @Autowired
    Person p;

    @ResponseBody
    @RequestMapping("path")
    public String path(){
        return "hello springboot";
    }

    /**
     * 视图解析测试
     * @param model
     * @return
     */
    @RequestMapping("demo")
    public String demo(Model model){
        model.addAttribute("msg","hello thymeleaf");

        List<Person> personList = new ArrayList<>();
        p.setName("jerry");
        p.setGender(1);
        personList.add(p);
        model.addAttribute("peoples",personList);
        return "demo";
    }

    @RequestMapping("fun")
    public String fun(Model model){
        model.addAttribute("Str","str built-in function");
        model.addAttribute("Num",5.2365);
        model.addAttribute("Bool",true);
        model.addAttribute("Array", new Integer[]{1,2,3,4});
        model.addAttribute("List", Arrays.asList(new String[]{"a","b","c"}));
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        model.addAttribute("hashMap",map);
        Date date = new Date();
        model.addAttribute("Date",date.getTime());
        return "fun";
    }

    @RequestMapping("fragment")
    public String fragment(){
        return "fragment";
    }
    /********************************************************************************************************************/
    /********************************************************************************************************************/
    @Autowired
    private MyService service;

    @RequestMapping("queryall")
    @ResponseBody
    public List<User> queryAll(){
        return service.queryAll();
    }

    @RequestMapping("update")
    @ResponseBody
    public boolean updateUser(User user){
        return service.updateUser(user);
    }
}
