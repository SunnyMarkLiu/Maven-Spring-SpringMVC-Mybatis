package com.markliu.springmybatis.mapper;

import com.markliu.springmybatis.domain.po.Students;

/**
 * Author: markliu
 * Time  : 16-8-8 下午4:36
 */
public interface StudentsMapper {

    // mapper 接口中的方法名与 mapper.xml 配置文件中 statement 定义的 id 相等
    Students getStudentsById(int id) throws Exception;
}
