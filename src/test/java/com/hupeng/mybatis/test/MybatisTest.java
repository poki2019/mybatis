package com.hupeng.mybatis.test;

import com.hupeng.mybatis.bean.Department;
import com.hupeng.mybatis.bean.Employee;
import com.hupeng.mybatis.dao.EmployeeDynamicMapper;
import com.hupeng.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

/**
 * @package:com.hupeng.mybatis.test
 * @author:Administrator
 * @date:2021/3/3 17:58
 * @project:mybatis
 * @comments:
 **/

public class MybatisTest {
    private static final Logger log = Logger.getLogger(MybatisTest.class);
    @Test
    public void test1() throws Exception{
        String resource = "mybatis-config-spring.xml";//类路径下
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = employeeMapper.getEmpById(1);
        log.info(employee);
        sqlSession.close();//先关闭一级缓存，才能使用二级缓存

        SqlSession sqlSession2 = sqlSessionFactory.openSession();//使用新的一级缓存，发现使用了二级缓存
        EmployeeMapper employeeMapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee employee2 = employeeMapper2.getEmpById(1);
        log.info(employee2);//发现用了二级缓存
        log.info(employee == employee2);//直接相等

        sqlSession2.close();
    }

    @Test
    public void test2() throws Exception{
        String resource = "mybatis-config-spring.xml";//类路径下
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        Map params = new HashMap();
        params.put("name","junqi");
        sqlSession.selectOne("getEmpByName",params);
        sqlSession.close();
    }

    @Test
    public void test3() throws Exception{
        /**
         * 注意：mybatis可以自定义增删改的返回值类型（需要你改Mapper接口的返回值类型和Mapper.xml的返回值）
         * 包括：void、int、boolean、long以及他们的包装类
         * 因为数据增删改返回的是受影响的行的数量（>0返回对应的boolean值就是true）
         */
        //测试增删改
        String resource = "mybatis-config-spring.xml";//类路径下
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();//不会自动提交数据库事务，需要用户自己提交
        //sqlSessionFactory.openSession(true);//会自动提交数据库事务
        Employee employee = new Employee();
        employee.setEmail("77783782@qq.com");
        employee.setGender("1");
        employee.setLastName("daying");

        //sqlSession.insert("com.hupeng.mybatis.dao.EmployeeMapper.addEmp",employee);

        Employee emp = sqlSession.selectOne("com.hupeng.mybatis.dao.EmployeeMapper.getEmpById",3);
        emp.setLastName("wangdaying");

        //sqlSession.update("updateEmp",emp);

        sqlSession.delete("deleteEmpById",3);

        //需要手动提交事务
        sqlSession.commit();
        //关闭会话
        sqlSession.close();
    }


    @Test
    public void test4() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-spring.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();//记得要提交

        Employee employee = new Employee(null,"wangting","2323@pengpeng.cn","2",null);
        //sqlSession.insert("com.hupeng.mybatis.dao.EmployeeMapper.addEmp",employee);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        mapper.addEmp(employee);
        log.info("id:" + employee.getId());
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test5() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-spring.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        Map params = new HashMap();
        params.put("lastName","%hu%");
        List<Employee> emps = sqlSession.selectList("com.hupeng.mybatis.dao.EmployeeMapper.selectEmpsByLastNameLike", params);
        log.info(emps);
        sqlSession.close();
    }

    @Test
    public void test6() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-spring.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee empAndDeptById = mapper.getEmpByStepId(1);
        //女生Employee{id=6, lastName='wangting', email='2323@pengpeng.cn', gender='0', dept=Department{id=2, departmentName='研发部'}}
        //男生Employee{id=1, lastName='xiaopeng', email='xiaopeng', gender='1', dept=null}
        log.info(empAndDeptById);
        sqlSession.close();
    }

    @Test
    public void test7() throws Exception{
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config-spring.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sessionFactory.openSession();
        EmployeeDynamicMapper mapper = sqlSession.getMapper(EmployeeDynamicMapper.class);
        List<Employee> emps = new ArrayList<Employee>(){{
            add(new Employee(null,"xiaohua" + Math.random() * 10,"ere@rr.com","1",new Department(1,null,null)));
            add(new Employee(null,"xiaohua" + Math.random() * 10,"ere@rr.com","2",new Department(2,null,null)));
        }};
        mapper.addEmps(emps);
        sqlSession.commit();
        sqlSession.close();
    }
}
