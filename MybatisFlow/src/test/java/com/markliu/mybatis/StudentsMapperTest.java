package com.markliu.mybatis;

import com.markliu.mybatis.domain.po.Students;
import com.markliu.mybatis.domain.po.custom.StudentsCustom;
import com.markliu.mybatis.domain.vo.StudentsQueryVo;
import com.markliu.mybatis.mapper.StudentsMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Author: markliu
 * Time  : 16-8-8 下午4:44
 */
public class StudentsMapperTest {

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
    public void testFindStudentsById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        Students students = studentsMapper.getStudentsById(2);
        if (students != null) {
            System.out.println(students.toString());
        }

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testFindAllStudentsLikeName() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        List<Students> studentses = studentsMapper.getStudentsByName("MarkLiu");
        if (studentses != null && studentses.size() > 0) {
            System.out.println(studentses.toString());
        }

        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testInsertStudents() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        Students students = new Students("SunnyMarkLiu2", "SunnyMarkLiu101@gmail.com", new Date());
        studentsMapper.insertStudents(students);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testUpdateStudents() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        Students students = studentsMapper.getStudentsById(5);
        students.setName("月光水岸2");
        studentsMapper.updateStudents(students);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void deleteStudentsById() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        studentsMapper.deleteStudentsById(5);

        sqlSession.commit();
        // 关闭资源
        sqlSession.close();
    }

    @Test
    public void testFindStudentsList() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        // 添加查询条件
        StudentsCustom queryStudentsCustom = new StudentsCustom();
        queryStudentsCustom.setName("MarkLiu");
        queryStudentsCustom.setEmail("SunnyMarkLiu@163.com");
        studentsQueryVo.setStudentsCustom(queryStudentsCustom);

        List<StudentsCustom> studentsCustomList = studentsMapper.findStudentsList(studentsQueryVo);
        // 关闭资源
        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }

    @Test
    public void testFindStudentsListResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);

        // 构造复杂查询条件
        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        StudentsCustom studentsCustom = new StudentsCustom();
        studentsCustom.setName("MarkLiu");
        studentsCustom.setEmail("@163.com");
        studentsQueryVo.setStudentsCustom(studentsCustom);

        List<StudentsCustom> studentsCustomList =
                studentsMapper.findStudentsListResultMap(studentsQueryVo);

        // 关闭资源
        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }

    @Test
    public void testFindStudentsListDynamicSql() throws  Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();

        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        // 构造复杂查询条件
        StudentsQueryVo studentsQueryVo = new StudentsQueryVo();
        StudentsCustom studentsCustom = new StudentsCustom();

        // 配置文件使用动态sql
        studentsCustom.setName("MarkLiu");
        studentsCustom.setEmail("@163.com");

        List<Integer> ids = new ArrayList<Integer>();
        ids.add(6);
        ids.add(7);
        studentsQueryVo.setIds(ids);

        studentsQueryVo.setStudentsCustom(studentsCustom);

        List<StudentsCustom> studentsCustomList =
                studentsMapper.findStudentsListDynamicSql(studentsQueryVo);

        sqlSession.close();

        System.out.println(studentsCustomList.toString());
    }

	/**
	 * 测试一级缓存
	 * @throws Exception
	 */
	@Test
    public void testFirstLavelCache() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        StudentsMapper studentsMapper = sqlSession.getMapper(StudentsMapper.class);
        Students students = studentsMapper.getStudentsById(2);
		System.out.println(students + students.toString());

		// 如果中间进行了update、insert、delete操作，提交事物 commit 之后会清空缓存
		// sqlSession.clearCache(); // 清空缓存
		students.setName("LiuQing");
		studentsMapper.updateStudents(students);
		sqlSession.commit();

		Students students2 = studentsMapper.getStudentsById(2);
		System.out.println(students2 + students2.toString());

		System.out.println(students == students2);	// 一级缓存有效时，输出 true

        // 关闭资源
        sqlSession.close();
    }

	/**
	 * 测试二级缓存
	 * 需要缓存的对象需要实现序列化接口，约为缓存的数据不一定缓存在内存当中。
	 * @throws Exception
	 */
	@Test
	public void testSecondLavelCache() throws Exception {
		SqlSession sqlSession1 = sqlSessionFactory.openSession();
		SqlSession sqlSession2 = sqlSessionFactory.openSession();
		SqlSession sqlSession3 = sqlSessionFactory.openSession();

		StudentsMapper studentsMapper = sqlSession1.getMapper(StudentsMapper.class);
		Students students = studentsMapper.getStudentsById(2);
		System.out.println(students.toString());
		sqlSession1.close();

		StudentsMapper studentsMapper3 = sqlSession3.getMapper(StudentsMapper.class);
		Students students3 = studentsMapper3.getStudentsById(2);
		students3.setName("SunnyMarkLiu");
		studentsMapper3.updateStudents(students3);
		// 需要 commit 提交操作，才能清空二级缓存的数据
		sqlSession3.commit();
		sqlSession3.close();

		StudentsMapper studentsMapper2 = sqlSession2.getMapper(StudentsMapper.class);
		Students students2 = studentsMapper2.getStudentsById(2);
		System.out.println(students2.toString());
		// 虽然没有发出 sql，从缓存中获取的数据，可仍然输出 false？
		System.out.println(students == students2);
		sqlSession2.close();
	}
}
