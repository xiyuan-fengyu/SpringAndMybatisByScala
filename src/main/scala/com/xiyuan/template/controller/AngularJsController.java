package com.xiyuan.template.controller;

import com.google.gson.JsonObject;
import com.xiyuan.template.util.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * Created by xiyuan_fengyu on 2016/12/15.
 */
@Controller
public class AngularJsController {

    @RequestMapping(value = "angularJs")
    public String angularJs() {
        return "angularJs/index";
    }

    @RequestMapping(value = "angularJs/persons")
    @ResponseBody
    public JsonObject persons() {
        List<Person> persons = Arrays.asList(
                new Person(1, "Tom", 18, 0),
                new Person(2, "Catalina", 18, 1),
                new Person(3, "Cat", 20, 0),
                new Person(4, "Lucy", 24, 1),
                new Person(5, "Kite", 15, -1)
        );
        return ResponseUtil.success("获取用户列表成功", persons);
    }

    private class Person {
        int id;
        String name;
        int age;
        int sex;
        Person(int id, String name, int age, int sex) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
    }

}
