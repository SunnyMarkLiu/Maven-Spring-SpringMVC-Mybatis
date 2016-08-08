package com.markliu.mybatis.mapper;

import com.markliu.mybatis.domain.Students;

import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午4:36
 */
public interface StudentsMapper {

    // mapper 接口中的方法名与 mapper.xml 配置文件中 statement 定义的 id 相等
    Students getStudentsById(int id) throws Exception;

    List<Students> getStudentsByName(String name) throws Exception;

    void insertStudents(Students students) throws Exception;

    void deleteStudentsById(int id) throws Exception;

    void updateStudents(Students students) throws Exception;
}
