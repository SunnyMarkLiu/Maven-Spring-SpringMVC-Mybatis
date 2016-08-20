package com.markliu.mybatis_advance;

import com.markliu.mybatis_advance.domain.po.Orders;
import com.markliu.mybatis_advance.domain.po.User;
import com.markliu.mybatis_advance.mapper.OrdersMapper;
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
 * Time  : 16-8-20 下午7:04
 */
public class LazyLoading {

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
	public void testLazyLoading() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper userMapper = sqlSession.getMapper(OrdersMapper.class);
		List<Orders> ordersList = userMapper.selectOrdersAndUserLazyLoading();
		for (Orders orders : ordersList) {
			User user = orders.getUser();
			System.out.println(user.toString());
		}
		sqlSession.close();
	}
}
