package com.hupeng.mybatis.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @package:com.hupeng.mybatis.bean
 * @author:Administrator
 * @date:2021/3/16 17:50
 * @project:mybatis
 * @comments:
 **/

public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }
    //必须提供无参构造，如只提供有参构造，该级联属性的属性不能正确被mybatis映射，比如id
    public Department() {

    }

    public Department(Integer id, String departmentName, List<Employee> emps) {
        this.id = id;
        this.departmentName = departmentName;
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
