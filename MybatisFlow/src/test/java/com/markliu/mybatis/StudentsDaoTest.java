package com.markliu.mybatis;

import com.markliu.mybatis.dao.StudentsDao;
import com.markliu.mybatis.dao.impl.StudentsDaoImpl;
import com.markliu.mybatis.domain.po.Students;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午3:22
 */
public class StudentsDaoTest {

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

    private StudentsDao studentsDao;

    @Before
    public void before() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        studentsDao = new StudentsDaoImpl(sqlSessionFactory);
    }

    @Test
    public void testFindStudentsById() throws Exception {
        Students students = studentsDao.findStudentsById(2);
        if (students != null) {
            System.out.println(students.toString());
        }
    }

    @Test
    public void testFindAllStudentsLikeName() throws Exception {
        List<Students> studentses = studentsDao.findAllStudentsLikeName("MarkLiu");
        if (studentses != null && studentses.size() > 0) {
            System.out.println(studentses.toString());
        }
    }

    @Test
    public void testInsertStudents() throws Exception {
        Students students = new Students("SunnyMarkLiu", "SunnyMarkLiu@gmail.com", new Date());
        studentsDao.insertStudents(students);
    }

    @Test
    public void testUpdateStudents() throws Exception {
        Students students = studentsDao.findStudentsById(3);
        students.setName("月光水岸");
        studentsDao.updateStudents(students);
    }

    @Test
    public void deleteStudentsById() throws Exception {
        studentsDao.deleteStudentsById(4);
    }
}
