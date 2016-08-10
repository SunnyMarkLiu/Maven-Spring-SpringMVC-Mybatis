package com.markliu.mybatis;

import com.markliu.mybatis.domain.po.Students;
import com.markliu.mybatis.domain.po.custom.StudentsCustom;
import com.markliu.mybatis.domain.vo.StudentsQueryVo;
import com.markliu.mybatis.mapper.StudentsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午4:44
 */
public class StudentsMapperTest {

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

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        String resource = "config/SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindStudentsById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        Students students = studentsMapper.getStudentsById(2);
        if (students != null) {
            System.out.println(students.toString());
        }

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testFindAllStudentsLikeName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        List<Students> studentses = studentsMapper.getStudentsByName("MarkLiu");
        if (studentses != null && studentses.size() > 0) {
            System.out.println(studentses.toString());
        }

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testInsertStudents() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        Students students = new Students("SunnyMarkLiu2", "SunnyMarkLiu101@gmail.com", new Date());
        studentsMapper.insertStudents(students);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testUpdateStudents() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        Students students = studentsMapper.getStudentsById(5);
        students.setName("月光水岸2");
        studentsMapper.updateStudents(students);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void deleteStudentsById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        studentsMapper.deleteStudentsById(5);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testFindStudentsList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        // 添加查询条件
        StudentsCustom queryStudentsCustom = new StudentsCustom();
        queryStudentsCustom.setName("MarkLiu");
        queryStudentsCustom.setEmail("SunnyMarkLiu@163.com");
        studentsQueryVo.setStudentsCustom(queryStudentsCustom);

        List<StudentsCustom> studentsCustomList = studentsMapper.findStudentsList(studentsQueryVo);
        // 关闭资源
        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }

    @Test
    public void testFindStudentsListResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        // 构造复杂查询条件
        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        StudentsCustom studentsCustom = new StudentsCustom();
        studentsCustom.setName("MarkLiu");
        studentsCustom.setEmail("@163.com");
        studentsQueryVo.setStudentsCustom(studentsCustom);

        List<StudentsCustom> studentsCustomList =
                studentsMapper.findStudentsListResultMap(studentsQueryVo);

        // 关闭资源
        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }

    @Test
    public void testFindStudentsListDynamicSql() throws  Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        // 构造复杂查询条件
        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        StudentsCustom studentsCustom = new StudentsCustom();

        // 配置文件使用动态sql
        studentsCustom.setName("MarkLiu");
        studentsCustom.setEmail("@163.com");

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(6);
        ids.add(7);
        studentsQueryVo.setIds(ids);

        studentsQueryVo.setStudentsCustom(studentsCustom);

        List<StudentsCustom> studentsCustomList =
                studentsMapper.findStudentsListDynamicSql(studentsQueryVo);

        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }
}
