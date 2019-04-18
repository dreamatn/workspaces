package com.hisun.ibscore.app.br.mapper;

import com.hisun.ibscore.app.br.domain.Br;
import com.hisun.ibscore.app.bp.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
//这个是Dao层  就是和数据库交互的一层  写成接口就可以了   MyBatis 可以使用逆向工程生成这些东西  逆向功能也能生成domain里面的Br 但是听考拉说一般不会用这个
//会自动注册到Spring容器里   方便其他类的注入
@Mapper
public interface BrMapper {
    void insert(Br br);

    void delete(int br);

    void update(Br br);

    Object findByKey(int br);

    List<User> findList();
}
