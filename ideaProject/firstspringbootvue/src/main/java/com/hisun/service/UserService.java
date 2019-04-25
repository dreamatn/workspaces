package com.hisun.service;

import java.util.List;
import java.util.Map;

import com.hisun.domain.MyResult;
import com.hisun.domain.User;

/**
 * Created by MoHuaiyi
 * 2019/4/17 11:06
 */
public interface UserService {
    MyResult login(User user);

    // 返回类型可以是Map
    List<Map<Object, String>> getMapList(String userName);

    // 返回类型是User
    List<User> getUserList(User user);

}
