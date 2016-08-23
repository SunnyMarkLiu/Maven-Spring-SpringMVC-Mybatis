package com.markliu.ssm.generator;

import org.apache.ibatis.io.Resources;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-23 上午11:09
 */
public class MybatisGenerator {

	// 加载 log4j 配置文件
	static {
		try {
			String resource = "log4j.properties";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			PropertyConfigurator.configure(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void generator() throws Exception {
		List<String> warnings = new ArrayList<String>();
		File configFile = new File("/media/markliu/Entertainment/Linux/SoftwareInformation/java/My_IntelliJ_projects/Spring-SpringMVC-Mybatis/src/test/resources/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
