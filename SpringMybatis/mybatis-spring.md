# Mybatis 整合 Spring 的基本步骤
## 1. pom.xml 添加依赖 dependency
```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!-- spring version -->
    <spring.version>4.2.5.RELEASE</spring.version>
    <mybatis.version>3.2.8</mybatis.version>
</properties>

<dependencies>
    <!-- spring-core -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- spring-context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- Spring AOP -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- AspectJ Runtime -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjrt</artifactId>
        <version>1.8.6</version>
    </dependency>

    <!-- AspectJ Weaver -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.8.6</version>
    </dependency>

    <!-- Spring Jdbc 的支持 -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>4.2.5.RELEASE</version>
    </dependency>

    <!-- c3p0 datasource -->
    <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>0.9.1</version>
    </dependency>

    <!-- mysql-connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.38</version>
    </dependency>

    <!-- mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>${mybatis.version}</version>
    </dependency>

    <!-- cglib javassist -->
    <dependency>
        <groupId>cglib</groupId>
        <artifactId>cglib</artifactId>
        <version>3.1</version>
    </dependency>
    <dependency>
        <groupId>org.javassist</groupId>
        <artifactId>javassist</artifactId>
        <version>3.17.1-GA</version>
    </dependency>

    <!-- logger -->
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.1.1</version>
    </dependency>
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>1.7.5</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.0.2</version>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>1.7.5</version>
    </dependency>

    <!-- mybatis-spring 整合 -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.2</version>
    </dependency>

    <!-- junit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.10</version>
        <scope>test</scope>
    </dependency>

</dependencies>
```

## 3. 创建基本的目录结构
![项目基本目录结构](https://camo.githubusercontent.com/e696f05a72a20d64e15279bc163e182afd630ba3/687474703a2f2f696d672e626c6f672e6373646e2e6e65742f3230313630383231313832333032393230)

## 4. applicationContext.xml中配置 SqlSessionFactory 和 DataSource
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.2.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.2.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.2.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.2.xsd">

	<!-- 引入数据库配置文件 -->
	<context:property-placeholder location="classpath:config/db.properties" />

	<!-- 配置C3P0 数据源 -->
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
		<property name="user" value="${jdbc.user}" />
		<property name="password" value="${jdbc.password}" />
		<property name="driverClass" value="${jdbc.driver}" />
		<property name="jdbcUrl" value="${jdbc.url}" />
		<property name="initialPoolSize" value="${jdbc.initialPoolSize}" />
		<property name="maxPoolSize" value="${jdbc.maxPoolSize}" />
	</bean>

	<!-- 配置 SqlSessionFactory -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!-- 加载mybatis的配置文件 -->
        <property name="configLocation" value="classpath:config/mybatis/SqlMapConfig.xml" />
        <!-- 加载数据源 -->
        <property name="dataSource" ref="dataSource" />
    </bean>

    <!-- 原始 Dao 方式 -->
    <bean id="studentsDao" class="com.markliu.springmybatis.dao.impl.StudentsDaoImpl" >
        <property name="sqlSessionFactory" ref="sqlSessionFactory" />
    </bean>
</beans>
```
## 5. 原始 Dao 和 Mapper 代理两种开发模式
### 5.1 原始 Dao 开发模式
#### 5.1.1 开发原始 Dao，并在 applicationContext 中配置 bean
```
public interface StudentsDao {
    Students findStudentsById(Integer id) throws Exception;
}

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
```
```
<!-- 原始 Dao 方式 -->
<bean id="studentsDao" class="com.markliu.springmybatis.dao.impl.StudentsDaoImpl" >
    <property name="sqlSessionFactory" ref="sqlSessionFactory" />
</bean>
```

### 5.2 Mapper 代理开发模式
#### 5.2.1 创建 Mapper 接口和 mapper.xml 配置文件
```
public interface StudentsMapper {

    // mapper 接口中的方法名与 mapper.xml 配置文件中 statement 定义的 id 相等
    Students getStudentsById(int id) throws Exception;
}
```
```
<mapper namespace="com.markliu.springmybatis.mapper.StudentsMapper">

    <select id="getStudentsById" parameterType="int" resultType="com.markliu.springmybatis.domain.po.Students">
        SELECT * FROM students WHERE students.stud_id = #{stud_id}
    </select>

</mapper>
```
#### 5.2.2 applicationContext.xml 中配置 MapperFactoryBean
##### 5.2.2.1 通过MapperFactoryBean创建代理对象。
```
    <!-- 通过MapperFactoryBean创建代理对象
     查看源代码，MapperFactoryBean<T> extends SqlSessionDaoSupport，所以需要传入 sqlSessionFactory;
     mapperInterface: 指定代理对象所要实现的接口
     -->
    <bean id="studentsMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
        <property name="sqlSessionFactory" ref="sqlSessionFactory"/>
        <property name="mapperInterface" value="com.markliu.springmybatis.mapper.StudentsMapper"/>
    </bean>
```
注意：此种方法存在如下缺陷，当 Mapper 较多时，需要配置多个不同的 MapperFactoryBean，较为繁琐；
可采用自动包扫描的方式。
##### 5.2.2.2 通过MapperScannerConfigurer进行mapper扫描（建议使用）
```
    <!-- 通过MapperScannerConfigurer进行mapper扫描（建议使用）
     mapper 批量扫描，从 mapper 包中扫描出 mapper 接口，自动创建代理对象，并在 spring 容器中注册名陈为类名首字母小写。
     遵循的规范：将 mapper.java 和 mapper.xml 映射文件名陈一致，且在同一个目录
     -->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!-- 指定扫描的包，如果存在多个包使用(逗号,)分割 -->
        <property name="basePackage" value="com.markliu.springmybatis.mapper" />
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory" />
    </bean>
```

## 6 测试代码
```
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
```
