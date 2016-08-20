package com.markliu.mybatis_advance;

import com.markliu.mybatis_advance.domain.po.User;
import com.markliu.mybatis_advance.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-20 下午4:56
 */
public class Many2ManyMapping {

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
	public void testSelectUserAndItems() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> users = userMapper.selectUserAndItems();
		for (User user : users) {
			System.out.println(user.toString());
		}
		sqlSession.close();
	}
}
