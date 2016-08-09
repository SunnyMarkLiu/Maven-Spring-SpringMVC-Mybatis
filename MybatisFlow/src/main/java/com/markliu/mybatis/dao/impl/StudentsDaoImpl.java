package com.markliu.mybatis.dao.impl;

import com.markliu.mybatis.dao.StudentsDao;
import com.markliu.mybatis.domain.po.Students;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午3:07
 */
public class StudentsDaoImpl implements StudentsDao{

    private SqlSessionFactory sqlSessionFactory;

    public StudentsDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public Students findStudentsById(Integer id) throws Exception {

        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsById";
        Students students = sqlSession.selectOne(statement, id);
        sqlSession.close();
        return students;
    }

    public List<Students> findAllStudentsLikeName(String likename) throws Exception {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        String statement = "com.markliu.mybatis.domain.StudentsMapper.getStudentsByName";
        List<Students> studentsList = sqlSession.selectList(statement, likename);
        sqlSession.close();
        return studentsList;
    }

    public void insertStudents(Students students) throws Exception {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        String statement = "com.markliu.mybatis.domain.StudentsMapper.insertStudents";
        sqlSession.insert(statement, students);
        sqlSession.commit();
        sqlSession.close();
    }

    public void updateStudents(Students students) throws Exception {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        String statement = "com.markliu.mybatis.domain.StudentsMapper.updateStudents";
        sqlSession.update(statement, students);
        sqlSession.commit();
        sqlSession.close();
    }

    public void deleteStudentsById(Integer id) throws Exception {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        String statement = "com.markliu.mybatis.domain.StudentsMapper.deleteStudentsById";
        sqlSession.delete(statement, id);
        sqlSession.commit();
        sqlSession.close();
    }
}
