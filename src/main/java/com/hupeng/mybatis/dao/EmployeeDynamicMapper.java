package com.hupeng.mybatis.dao;

import com.hupeng.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @package:com.hupeng.mybatis.dao
 * @author:Administrator
 * @date:2021/4/9 17:03
 * @project:mybatis
 * @comments:
 **/

public interface EmployeeDynamicMapper {
    List<Employee> getEmpByemp(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> selectForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);
}
