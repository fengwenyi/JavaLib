package com.fengwenyi.javalib.convert;

import java.util.List;
import java.util.Map;

/**
 * @author Erwin Feng
 * @since 2020/8/20
 */
public class ErwinEntity {

    private String name;

    private Integer age;

    private String sex;

    private List<String> likes;

    private Map<String, String> other;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    public Map<String, String> getOther() {
        return other;
    }

    public void setOther(Map<String, String> other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "ErwinEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", likes=" + likes +
                ", other=" + other +
                '}';
    }
}
