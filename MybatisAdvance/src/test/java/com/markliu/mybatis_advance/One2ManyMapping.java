package com.markliu.mybatis_advance;

import com.markliu.mybatis_advance.domain.po.Orderdetail;
import com.markliu.mybatis_advance.domain.po.Orders;
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
 * Time  : 16-8-20 上午10:51
 */
public class One2ManyMapping {
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
	public void testOne2ManyMapping() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
		List<Orders> ordersList =
				ordersMapper.selectselectOrdersUserAndOrderDetailsByResultMap();

		for (Orders orders : ordersList) {
			System.out.println(orders.toString());
		}

		sqlSession.close();
	}
}
