package com.markliu.springmybatis;

import com.markliu.springmybatis.domain.po.Students;
import com.markliu.springmybatis.mapper.StudentsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * Author: markliu
 * Time  : 16-8-22 上午9:40
 */
public class MapperTest {
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

	private ApplicationContext applicationContext;

	@Before
	public void setApplicationContext() {
		applicationContext = new ClassPathXmlApplicationContext("classpath:config/spring/applicationContext.xml");
	}

	@Test
	public void testFindStudentsById() throws Exception {
		StudentsMapper studentsMapper = (StudentsMapper) applicationContext.getBean("studentsMapper");

		// 获取动态代理对象所实现的接口，com.markliu.springmybatis.mapper.StudentsMapper
		Class<?>[] interfaces = studentsMapper.getClass().getInterfaces();
		for (Class clazz : interfaces) {
			System.out.println(clazz.getName());
		}

		Students students = studentsMapper.getStudentsById(2);
		System.out.println(students.toString());
	}
}
