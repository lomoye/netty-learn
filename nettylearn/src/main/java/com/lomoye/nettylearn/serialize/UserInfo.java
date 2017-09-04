package com.lomoye.nettylearn.serialize;

import org.msgpack.annotation.Message;

/**
 * Created by lomoye on 2017/9/2.
 *
 */
@Message
public class UserInfo {
    private Integer age;

    private String name;


    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
