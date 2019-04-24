package com.hisun.dao;

import com.hisun.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

//    public User getByPassword(User user);
//    List<User> login(User user);
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(User record);
//
//    int insertSelective(User record);
//
//    User selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(User record);
//
//    int updateByPrimaryKey(User record);


    // 方法名和mapper映射文件userDao.xml中的sql语句的id一致，参数类型与parameterType一致。
    // 返回类型可以是Map
    List<Map<Object, String>> getMapList(String userName);
//    Map<Object, String> getMapList(String userName);

    // 返回类型是User
    List<User> getUserList(User user);



}