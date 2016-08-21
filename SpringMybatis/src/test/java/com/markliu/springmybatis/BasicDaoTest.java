package com.markliu.springmybatis;

import com.markliu.springmybatis.dao.StudentsDao;
import com.markliu.springmybatis.domain.po.Students;
import org.apache.ibatis.io.Resources;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: markliu
 * Time  : 16-8-21 下午11:34
 */
public class BasicDaoTest {

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
		StudentsDao studentsDao = (StudentsDao) applicationContext.getBean("studentsDao");
		Students students = studentsDao.findStudentsById(2);
		System.out.println(students.toString());
	}
}
