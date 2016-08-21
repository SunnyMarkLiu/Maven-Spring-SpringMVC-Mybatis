package com.markliu.springmybatis.dao.impl;

import com.markliu.springmybatis.dao.StudentsDao;
import com.markliu.springmybatis.domain.po.Students;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 原始 Dao 方式开发，注意可通过继承 SqlSessionDaoSupport，该类中封装了 sqlSession,
 * 提供了 setSqlSessionFactory 方法，所以只需要配置 sessionFactory，即可实现注入
 * Author: markliu
 * Time  : 16-8-8 下午3:07
 */
public class StudentsDaoImpl extends SqlSessionDaoSupport implements StudentsDao {

	public Students findStudentsById(Integer id) throws Exception {

		SqlSession sqlSession = this.getSqlSession();
		String statement = "com.markliu.springmybatis.mapper.StudentsMapper.getStudentsById";
		Students students = sqlSession.selectOne(statement, id);
		// java.lang.UnsupportedOperationException:
		// Manual close is not allowed over a Spring managed SqlSession
		// sqlSession.close();
		return students;
	}

}
