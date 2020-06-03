package com.test;

import com.dao.IPersonDao;
import com.domain.Person;
import com.domain.QueryVo;
import jdk.jshell.spi.SPIResolutionException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试mybatis的CRUD操作
 */
public class MyBatisTest {

    private InputStream in;
    private SqlSession session;
    private IPersonDao personDao;

    @Before  // 用于在测试方法执行之前执行
    public void init() throws IOException {
        // 加载配置文件
        in = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 创建SqlSessionFactory
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        // 创建SqlSession对象
        session = factory.openSession();
        // 获取到代理对象
        personDao = session.getMapper(IPersonDao.class);
    }

    @After // 用于在测试方法执行之后执行
    public void destroy() throws IOException {
        // 提交事务
        session.commit();
        session.close();
        in.close();
    }
    /**
     * 测试查询所有
     */
    @Test
    public  void testFindAll() throws IOException {

        List<Person> list = personDao.findAll();
        for(Person person:list){
            System.out.println(person);
        }

    }

    /**
     * 测试保存用户
     * @throws IOException
     */
    @Test
    public void testSaveUser() throws IOException {
        Person person = new Person();
        person.setName("hehe");
        person.setMoney(666f);
        System.out.println("保存之前"+person);
        personDao.saveUser(person);
        System.out.println("保存之后"+person);
    }

    /**
     * 测试更新用户
     * @throws IOException
     */
    @Test
    public void updateUser() throws IOException {
        Person person = new Person();
        person.setId(3);
        person.setName("mybaits update user");
        person.setMoney(666f);
        personDao.updateUser(person);

    }

    /**
     * 测试删除用户
     * @throws IOException
     */
    @Test
    public void deleteUser() throws IOException {
        personDao.deleteUser(7);
    }

    /**
     * 查询一个
     * @throws IOException
     */
    @Test
    public void findUserbyId() throws IOException {
        Person person = personDao.findById(1);
        System.out.println(person);
    }

    /**
     * 测试根据名字模糊查询
     * @throws IOException
     */
    @Test
    public void findByName() throws IOException {
        List<Person> persons = personDao.findByName("%a%");
        for (Person p:persons) {
            System.out.println(p);
        }
    }

    /**
     * 查询总用户数
     * @throws IOException
     */
    @Test
    public void findTotal() throws IOException {
        int cnt = personDao.findTotal();
        System.out.println(cnt);
    }

    /**
     * 测试QueryVo查询 使用实体类的包装对象作为查询
     * @throws IOException
     */
    @Test
    public void findUserByQueryVo() throws IOException {
        QueryVo vo = new QueryVo();
        Person p = new Person();
        p.setMoney(200f);
        p.setName("%a%");
        vo.setPerson(p);
        List<Person>list = personDao.findUserByVo(vo);
        for(Person person:list){
            System.out.println(person);
        }
    }

    // 动态查询
    /**
     * 条件查询
     * @throws IOException
     */
    @Test
    public void findUserByCondition() throws IOException {
        Person p = new Person();
        p.setName("aaa");
        p.setMoney(11f);
        List<Person> list = personDao.findUserByCondition(p);
        for(Person person:list){
            System.out.println(person);
        }
    }


    /**
     * 根据QueryVo中提供的id集合进行查询
     * @throws IOException
     */
    @Test
    public void findUserByIds() throws IOException {
        QueryVo vo = new QueryVo();
        List<Integer>list = new ArrayList<>();
        list.add(1);
        list.add(3);
        vo.setIds(list);
        List<Person> personList = personDao.findUserInIds(vo);
        for(Person person:personList){
            System.out.println(person);
        }

    }


}
