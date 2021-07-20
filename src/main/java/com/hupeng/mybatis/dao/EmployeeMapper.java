package com.hupeng.mybatis.dao;

import com.hupeng.mybatis.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @package:com.hupeng.mybatis.bean
 * @author:Administrator
 * @date:2021/3/6 10:43
 * @project:mybatis
 * @comments:
 **/

public interface EmployeeMapper {//不用写接口实现类，只需要与对应的sql文件对应-mybatis动态绑定，就能调sql映射文件中配置的sql
    public Employee getEmpById(Integer id);
    //获取多条条记录，map中key为这条记录的id（不是列名），value是这条记录封装后的JavaBean
    @MapKey("id")//告诉mybatis封装map的时候该用JavaBean的哪个属性的值作为key
    public Map<Integer,Employee> getEmpByLastNameLikeMap(String lastName);
    //获取一条记录，map中key为列名，value为查出的值
    public Map<String,Object> getEmpByIdMap(Integer id);

    public void addEmp(Employee employee);

    public void updateEmp(Employee employee);

    public void deleteEmpById(Integer id);

    //查询员工及其对应的部门信息
    public Employee getEmpAndDeptById(Integer id);

    public Employee getEmpByStepId(Integer id);

    public List<Employee> getEmpsByDeptId(Integer id);//根据部门查员工
}
