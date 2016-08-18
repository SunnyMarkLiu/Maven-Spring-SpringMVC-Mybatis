package com.markliu.mybatis_advance;

import com.markliu.mybatis_advance.domain.po.Items;
import com.markliu.mybatis_advance.domain.po.Orderdetail;
import com.markliu.mybatis_advance.domain.po.Orders;
import com.markliu.mybatis_advance.domain.po.User;
import com.markliu.mybatis_advance.mapper.ItemsMapper;
import com.markliu.mybatis_advance.mapper.OrderdetailMapper;
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
import java.util.Date;

/**
 * Author: markliu
 * Time  : 16-8-18 下午5:54
 */
public class InsertDataTest {

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
	public void testInsertUser() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		User user = new User();
		user.setUsername("Rose");
		user.setBirthday(new Date());
		user.setSex("female");
		user.setAddress("WuHan");
		userMapper.insertUser(user);

		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testInsertItems() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);

		Items items = new Items();
		items.setName("Basketball");
		items.setCreatetime(new Date());
		items.setDetail("nice Basketball");
		items.setPicture("img/basketball.jpg");
		items.setPrice(600f);

		itemsMapper.insertItems(items);

		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testInsertOrders() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);

		Orders orders = new Orders();
		orders.setUserId(2);
		orders.setCreatetime(new Date());
		orders.setNote("user id=2 buy things");
		orders.setNumber("order000002");

		ordersMapper.insertOrders(orders);

		sqlSession.commit();
		sqlSession.close();
	}

	@Test
	public void testInsertOrderdetail() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		OrderdetailMapper orderdetailMapper = sqlSession.getMapper(OrderdetailMapper.class);

		Orderdetail orderdetail = new Orderdetail();
		orderdetail.setItemsId(2);
		orderdetail.setItemsNum(1);
		orderdetail.setOrdersId(1);

		orderdetailMapper.insertOrderdetail(orderdetail);

		sqlSession.commit();
		sqlSession.close();
	}
}
