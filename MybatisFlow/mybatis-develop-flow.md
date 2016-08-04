# Mybatis Basic Develop Flow
## 1. Add dependencies
```
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>0.9.1</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.2.8</version>
        </dependency>
        <dependency>
            <groupId>cglib</groupId>
            <artifactId>cglib</artifactId>
            <version>3.1</version>
        </dependency>
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
            <groupId>org.javassist</groupId>
            <artifactId>javassist</artifactId>
            <version>3.17.1-GA</version>
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
```
## 2. log4j.properties
```
    # Global logging configuration
    # 配置根Logger，语法log4j.rootLogger = level,appenderName1,appenderName2,
    # 开发环境下日志级别设置为 DEBUG，实际部署时设置成 info 或 error
    # stdout : org.apache.log4j.ConsoleAppender
    log4j.rootLogger=DEBUG, stdout
    
    # console output
    log4j.appender.stdout=org.apache.log4j.ConsoleAppender
    log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
    log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```
## 3. Mybatis的全局配置文件 SqlMapConfig.xml
配置mybatis的运行环境，数据源、事务等。
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <!-- environment 元素体中包含了事务管理和连接池的配置 -->
    <!-- 注意和Spring整合之后，environment 配置将废除 -->
    <environments default="mybatis-environment">
        <environment id="development">
            <!-- 使用JDBC的事物管理，由mybatis管理 -->
            <transactionManager type="JDBC"/>
            <!-- 数据库连接池，此处由mybatis管理，和spring整合之后由第三方管理如C3P0 -->
            <dataSource type="POOLED">
                <!--${driver}-->
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <!--${url}-->
                <property name="url" value="jdbc:mysql:///springmvc_mybatis?useUnicode=true&amp;characterEncoding=UTF-8"/>
                <!--${username}-->
                <property name="username" value="root"/>
                <!--${password}-->
                <property name="password" value="992101"/>
            </dataSource>
        </environment>
    </environments>

    <!-- mappers 元素则是包含一组 mapper 映射器（这些 mapper 的 XML 文件包含了 SQL 代码和映射定义信息） -->
    <mappers>
        <mapper resource="config/sqlmap/Students.xml" />
    </mappers>

</configuration>
```
## 4 Create domain class
```
public class Students {

    private int stud_id;
    private String name;
    private String email;
    private Date dob;
    // setter and getter
}
```
## 5 配置domain类的映射文件
映射文件命名：User.xml（原始ibatis命名）;
mapper代理开发映射文件名称叫XXXMapper.xml，比如：UserMapper.xml、ItemsMapper.xml
在映射文件中配置sql语句。
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- namespace命名空间，其作用是对sql进行分类化管理，理解sql隔离
 注意使用namespace代理方法开发，namespace有特殊的含义
 -->
<mapper namespace="com.markliu.mybatis.domain.UserMapper">
    <!-- 在该 namespace 下配置 sql 语句 -->

    <!-- 通过select执行数据库查询，
     id：表示映射文件中的sql，将sql语句封装到mappedStatement对象中，所以将id称为 Statement 的 id
     parameterType：表示输入参数的类型，此处根据 id 查询，所以为int类型
     resultType：指定返回的结果类型
     #{stud_id}：#{}表示占位符，stud_id表述接受输入的参数，参数名为stud_id
     -->
    <select id="getUserById" parameterType="int" resultType="com.markliu.mybatis.domain.Students">
        SELECT * FROM User WHERE id = #{stud_id}
    </select>
</mapper>
```