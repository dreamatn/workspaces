package com.hisun.ibscore.app.bp.service.impl;

import com.hisun.ibscore.app.bp.domain.User;
import com.hisun.ibscore.app.bp.mapper.UserMapper;
import com.hisun.ibscore.app.bp.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

//    @Autowired
//    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
//        super.setSqlSessionFactory(sqlSessionFactory);
//    }
    @Autowired
    private UserMapper userMapper;

    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
    public void add(User user){
        userMapper.insert(user);
    }

    @Override
    public void delete(String tlr){
        userMapper.delete(tlr);
    }

    @Override
    public Object findByKey(String tlr){
        return userMapper.findByKey(tlr);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public Object findList(){
        return userMapper.findList();
    }

}
