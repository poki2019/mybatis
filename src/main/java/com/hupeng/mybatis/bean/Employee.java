package com.hupeng.mybatis.bean;

import java.io.Serializable;

/**
 * @package:com.hupeng.mybatis.bean
 * @author:Administrator
 * @date:2021/3/3 17:34
 * @project:mybatis
 * @comments:
 **/

public class Employee implements Serializable {
    private Integer id;
    private String lastName;//java类的属性与数据库表（last_name）中的属性名不对应，mybatis不知道该怎么映射--》需要在Mapper.xml中配置的sql给列名取别名
    private String email;
    private String gender;
    private Department dept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender, Department dept) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dept=" + dept +
                '}';
    }
}
