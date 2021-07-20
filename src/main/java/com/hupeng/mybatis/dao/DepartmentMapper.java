package com.hupeng.mybatis.dao;

import com.hupeng.mybatis.bean.Department;

/**
 * @package:com.hupeng.mybatis.dao
 * @author:Administrator
 * @date:2021/3/17 9:58
 * @project:mybatis
 * @comments:
 **/

public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByStep(Integer id);
}
