package com.hisun.ibscore.app.bp.mapper;

import com.hisun.ibscore.app.bp.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

//@Repository(value = "userMapper")//Dao层  对数据库进行操作Mybatis框架  是个接口  不用实例化
//就是不用具体的写其中的方法
@Mapper
public interface UserMapper  {

//    @Insert("INSERT INTO BPTTLT(tlr,tlr_br,tlr_cn_nm,tlr_en_nm) VALUES(#{tlr}, #{tlrBr}, #{tlrCnNm},#{tlrEnNm})")
//    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")   //keyProperty java对象的属性；keyColumn表示数据库的字段
    void insert(User user);

    void delete(String tlr);

    void update(User user);

    User findByKey(String tlr);

    List<User> findList();

}
