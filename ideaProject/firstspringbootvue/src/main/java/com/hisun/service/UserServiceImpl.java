package com.hisun.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisun.dao.UserDao;
import com.hisun.domain.MyResult;
import com.hisun.domain.User;

/**
 * Created by MoHuaiyi
 * 2019/4/17 11:07
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

//    @Autowired
//    private UserDao userDao;
//    @Override
//    public MyResult login(User user) {
//        User u = new User();
//        List<User> list = new ArrayList<>();
//        list=userDao.login(user);
//        MyResult result = new MyResult();
//        if (list.size()!=0){
//            result.setCode(0);
//            result.setMsg("登陆成功");
//            result.setList(list);
//            result.setObj(list.get(0));
//        } else {
//            result.setCode(1);
//            result.setMsg("登陆失败");
//        }
//        return result;
//    }

    @Autowired
    private UserDao userDao;
    @Override
    public MyResult login(User user){

        System.out.println("显示开始");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
        System.out.println(user.getVersionNo());
        System.out.println(user.isCompInd());
        System.out.println(user.isEncrInd());
        System.out.println(user.isTranInd());
        System.out.println(user.getReqType());
        System.out.println(user.getResendFlg());
        System.out.println(user.getReqChnl());
        System.out.println(user.getReqChnlJrn());
        System.out.println(user.getReqChnlDate());
        System.out.println(user.getReqChnlTime());
        System.out.println(user.getChnlTrId());
        System.out.println(user.getTrId());
        System.out.println(user.getTrBank());
        System.out.println(user.getTrBr());
        System.out.println(user.getTlld());

        User u = new User();
        List<User> list = new ArrayList<>();
        list = userDao.getUserList(user);
//        List<Map<Object,String>> list2 = new ArrayList<>();
//        Map<Object, String> list2 = new HashMap<>();
//        list2 = userDao.getMapList(user.getUsername());
//        list2 = userDao.getMapList(user.getUsername());

        System.out.println(list.get(0));
//        System.out.println(list2.get(0));
//        System.out.println(list2.get("username"));

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getInpData());
        System.out.println("显示结束");

//        list=userDao.login(user);

        MyResult result = new MyResult();
        result.setCode(0);
        result.setMsg("登陆成功!!!!");
//        result.setList(list);
        return result;
    }

    @Override
    public List<Map<Object, String>> getMapList(String userName) {
        return userDao.getMapList(userName);
    }

    @Override
    public List<User> getUserList(User user) {
        return userDao.getUserList(user);
    }


//    @Override
//    public MyResult login(User user){
//
//
//        MyResult result = new MyResult();
//        return result;
//    }
}
