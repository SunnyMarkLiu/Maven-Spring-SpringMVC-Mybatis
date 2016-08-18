package com.markliu.mybatis_advance;

import com.markliu.mybatis_advance.domain.po.Orders;
import com.markliu.mybatis_advance.domain.vo.OrdersCustom;
import com.markliu.mybatis_advance.mapper.OrdersMapper;
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
 * Time  : 16-8-18 下午11:05
 */
public class One2OneMapping {

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
	public void testOne2OneMapping() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		List<OrdersCustom> ordersCustomList = ordersMapper.selectOrdersAndUser();

		for (OrdersCustom ordersCustom : ordersCustomList) {
			System.out.println(ordersCustom.toString());
		}
		sqlSession.close();
	}

	@Test
	public void testOne2OneMappingResultMap() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		List<Orders> ordersList = ordersMapper.selectOrdersAndUserByResultMap();

		for (Orders orders : ordersList) {
			System.out.println(orders.toString());
		}
		sqlSession.close();
	}
}
