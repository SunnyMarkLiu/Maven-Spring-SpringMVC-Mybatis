package com.markliu.mybatis.dao;

import com.markliu.mybatis.domain.po.Students;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午3:07
 */
public interface StudentsDao {

    Students findStudentsById(Integer id) throws Exception;

    List<Students> findAllStudentsLikeName(String likename) throws Exception;

    void insertStudents(Students students) throws Exception;

    void updateStudents(Students students) throws Exception;

    void deleteStudentsById(Integer id) throws Exception;
}
