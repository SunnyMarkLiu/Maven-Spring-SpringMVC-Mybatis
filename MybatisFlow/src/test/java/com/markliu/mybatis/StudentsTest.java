package com.markliu.mybatis;

import com.markliu.mybatis.domain.po.Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-4 下午3:59
 */
public class StudentsTest {

    private SqlSession sqlSession;

    // 加载 log4j 配置文件
    static {
        try {
            String resource = "config/log4j.properties";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            PropertyConfigurator.configure(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Before
    public void getSqlSession() {
        try {
            // 1. 创建会话工厂 SqlSessionFactory
            String resource = "config/SqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 2. 通过会话工厂得到 SqlSession
            sqlSession = sqlSessionFactory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserById() {

        // 3. 通过 SqlSession 操作数据库
        // statement: 配置文件中配置的 statement 的 id，等于 namespace + "." + statement的id
        String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsById";
        int id = 1;
        Students students = sqlSession.selectOne(statement, id);
        System.out.println(students.toString());

    }

    @Test
    public void testGetStudentsByName() {
        String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsByName";

        /*
         模糊查询方法一：
            SELECT * FROM students WHERE name LIKE #{name}
            sqlSession.selectList(statement, "%Student%");
         模糊查询方法二：
            SELECT * FROM students WHERE name LIKE '%${name}%'
            sqlSession.selectList(statement, "Student");
          */
        List<Students> studentsList = sqlSession.selectList(statement, "Student");
        System.out.println(studentsList);
    }

    @Test
    public void testInsertStudents() {
        String statement = "com.markliu.mybatis.domain.StudentsMapper.insertStudents";
        Students students = new Students("MarkLiu", "SunnyMarkLiu@163.com", new Date());
        sqlSession.insert(statement, students);

        // 提交事物
        sqlSession.commit();

        // 获取插入数据的主键
        System.out.println(students.getStud_id());
    }

    @Test
    public void testDeleteStudentsById() {
        String statement = "com.markliu.mybatis.domain.StudentsMapper.deleteStudentsById";
        int result = sqlSession.delete(statement, 1);

        // 提交事物
        sqlSession.commit();
        System.out.println("影响的行数：" + result);
    }

    @Test
    public void testUpdateStudents() {
        String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsById";
        int id = 2;
        Students students = sqlSession.selectOne(statement, id);
        System.out.println(students.toString());

        students.setEmail("1291833546@qq.com");
        students.setDob(new Date());

        statement = "com.markliu.mybatis.domain.StudentsMapper.updateStudents";
        int result = sqlSession.update(statement, students);
        // 提交事物
        sqlSession.commit();
        System.out.println(students.toString());
        System.out.println("影响的行数：" + result);
    }

    @After
    public void close() {
        // 4. 关闭资源
        sqlSession.close();
    }
}
