package com.dao;

import com.domain.Person;
import com.domain.QueryVo;

import java.util.List;

public interface IPersonDao {
    /**
     * 查询所有
     * @return
     */
    List<Person> findAll();
    /**
     * 保存用户
     */
    void saveUser(Person person);
    /**
     * 更新用户
     */
    void updateUser(Person person);
    /**
     * 删除用户
     */
    void deleteUser(Integer userId);
    /**
     * 查询一个
     */
    Person findById(Integer id);
    /**
     * 根据名字模糊查询用户信息
     */
    List<Person> findByName(String name);
    /**
     * 查询总用户数
     */
    int findTotal();

    /**
     * 根据queryvo中的条件查询
     * @return
     */
    List<Person> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件查询
     */
    List<Person> findUserByCondition(Person person);
    /**
     * 根据QueryVo中的id集合查询用户信息
     */
    List<Person> findUserInIds(QueryVo vo);

}
