package com.markliu.mybatis;

import com.markliu.mybatis.domain.Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: markliu
 * Time  : 16-8-4 下午3:59
 */
public class StudentsTest {

    @Test
    public void testGetUserById() {

        try {
            // 1. 创建会话工厂 SqlSessionFactory
            String resource = "config/SqlMapConfig.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 2. 通过会话工厂得到 SqlSession
            SqlSession sqlSession = sqlSessionFactory.openSession();

            // 3. 通过 SqlSession 操作数据库
            // statement: 配置文件中配置的 statement 的 id，等于 namespace + "." + statement的id
            String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsById";
            int id = 1;
            Students students = sqlSession.selectOne(statement, id);
            System.out.println(students.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
