package com.markliu.springmybatis.dao;


import com.markliu.springmybatis.domain.po.Students;

/**
 * Author: markliu
 * Time  : 16-8-8 下午3:07
 */
public interface StudentsDao {

    Students findStudentsById(Integer id) throws Exception;
}
