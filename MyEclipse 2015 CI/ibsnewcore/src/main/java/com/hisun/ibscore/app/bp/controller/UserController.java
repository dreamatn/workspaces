package com.hisun.ibscore.app.bp.controller;

import com.hisun.ibscore.app.bp.constant.BpMsgConstant;
import com.hisun.ibscore.app.bp.domain.User;
import com.hisun.ibscore.app.bp.domain.UserG;
import com.hisun.ibscore.app.bp.mapper.UserGMapper;
import com.hisun.ibscore.app.bp.service.UserRule;
import com.hisun.ibscore.app.bp.service.UserService;
import com.hisun.ibscore.app.bp.service.UserServiceFactory;
import com.hisun.ibscore.core.annotation.IbsRequestBody;
import com.hisun.ibscore.core.annotation.IbsResponseBody;
import com.hisun.ibscore.core.config.IbsCoreConfigurer;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import com.hisun.ibscore.core.utils.IbsTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
//下面这个 Controller注解定义了这是一个SpringMVC的类
@Controller
//下面这个是一个自定义注解
@IbsResponseBody
//@ResponseBody
//这个定义了访问的路径如果是/ibscore/bp/user 就会使用到这个类
@RequestMapping("/ibscore/bp/user")
//使用事务管理
@Transactional(propagation = Propagation.REQUIRED)
public class UserController {

    @Autowired
    private UserServiceFactory userServiceFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private UserGMapper userGMapper;

    @PostMapping("add")
    public IbsGwaContext addUser(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {


        //业务逻辑
        User user = new User();
        user.setTlr((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString());
        user.setTlrBr(Integer.valueOf(ibsGwaContext.getBrDp()));
        user.setTlrCnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrName").toString());
        user.setTlrEnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrEName").toString());
        user.setSignSts("F");
        user.setTlrSts("N");
//        user.setExpDt((Date)("2018-10-01"));
//        user.setUpdDt(new Date());

        //Ioc 产品规则

        userServiceFactory.getUserRule((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrType").toString()).check(user);
        userServiceFactory.getUserService((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrType").toString()).add(user);
//        userRule.check(user);
//        userService.add(user);


        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk",ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr",ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo",user.toString());

        //test error
//        int i = 1/0;

        //
        System.out.print("---debug : UserController.addUser Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("delete")
    public IbsGwaContext deleteUser(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.deleteUser Tia \n");
        System.out.print("---debug : UserController.deleteUser Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务规则
        System.out.print("---debug : UserController.deleteUser Gwa TrId " +
                ibsGwaContext.getTrId() +
                "\n");

        System.out.print("---debug : UserController.deleteUser Awa tlrNo " +
                ((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString() +
                "\n");

        System.out.print("---debug : UserController.deleteUser Awa tlrPassword " +
                ibsGwaContext.getAwaArea().get("tlrPassword") +
                "\n");

        System.out.print("---debug : UserController.deleteUser Awa tlrName " +
                ibsGwaContext.getAwaArea().get("tlrName") +
                "\n");
        System.out.print("---debug : UserController.deleteUser Awa tlrEName " +
                ibsGwaContext.getAwaArea().get("tlrEName") +
                "\n\n");

        //业务逻辑
        userService.delete((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString());

        System.out.print("---debug : UserController.deleteUser ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("update")
    public IbsGwaContext updateUser(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.updateUser Tia \n");
        System.out.print("---debug : UserController.updateUser Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务规则
        System.out.print("---debug : UserController.updateUser Gwa TrId " +
                ibsGwaContext.getTrId() +
                "\n");

        System.out.print("---debug : UserController.updateUser Awa tlrNo " +
                ((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString() +
                "\n");

        System.out.print("---debug : UserController.updateUser Awa tlrPassword " +
                ibsGwaContext.getAwaArea().get("tlrPassword") +
                "\n");

        System.out.print("---debug : UserController.updateUser Awa tlrName " +
                ibsGwaContext.getAwaArea().get("tlrName") +
                "\n");
        System.out.print("---debug : UserController.updateUser Awa tlrEName " +
                ibsGwaContext.getAwaArea().get("tlrEName") +
                "\n\n");

        //业务逻辑
        User user = new User();
        user.setTlr((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString());
        user.setTlrBr(Integer.valueOf(ibsGwaContext.getBrDp()));
        user.setTlrCnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrName").toString());
        user.setTlrEnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrEName").toString());
        user.setSignSts("F");
        user.setTlrSts("N");
//        user.setUpdDt(new Date());

        userService.update(user);

        ibsGwaContext.getOutpArea().put("tlrInfo",user.toString());

        System.out.print("---debug : UserController.updateUser ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("find")
    public IbsGwaContext findUser(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.findUser Tia \n");
        System.out.print("---debug : UserController.findUser Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务规则
        System.out.print("---debug : UserController.findUser Gwa TrId " +
                ibsGwaContext.getTrId() +
                "\n");

        System.out.print("---debug : UserController.findUser Awa tlrNo " +
                ((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString() +
                "\n");

        System.out.print("---debug : UserController.findUser Awa tlrPassword " +
                ibsGwaContext.getAwaArea().get("tlrPassword") +
                "\n");

        System.out.print("---debug : UserController.findUser Awa tlrName " +
                ibsGwaContext.getAwaArea().get("tlrName") +
                "\n");
        System.out.print("---debug : UserController.findUser Awa tlrEName " +
                ibsGwaContext.getAwaArea().get("tlrEName") +
                "\n\n");

        //业务逻辑
        System.out.print("---debug : UserController.findUser ibsGwaContext find user first" +
                "\n\n");
        ibsGwaContext.getOutpArea().put("tlrInfo",((User)(userService.findByKey((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString()))).toString());
        System.out.print("---debug : UserController.findUser ibsGwaContext find user secend" +
                "\n\n");
//        ibsGwaContext.getOutpArea().put("tlrInfo2",((User)(userService.findByKey((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString()))).toString());
//        System.out.print("---debug : UserController.deleteUser ibsGwaContext find user third" +
//                "\n\n");
//        ibsGwaContext.getOutpArea().put("tlrInfo3",((User)(userService.findByKey("OP01"))).toString());

        System.out.print("---debug : UserController.findUser ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("brows")
    public IbsGwaContext browsUser(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.browsUser Tia \n");
        System.out.print("---debug : UserController.browsUser Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务规则
        System.out.print("---debug : UserController.browsUser Gwa TrId " +
                ibsGwaContext.getTrId() +
                "\n");

        System.out.print("---debug : UserController.browsUser Awa tlrNo " +
                ((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString() +
                "\n");

        System.out.print("---debug : UserController.browsUser Awa tlrPassword " +
                ibsGwaContext.getAwaArea().get("tlrPassword") +
                "\n");

        System.out.print("---debug : UserController.browsUser Awa tlrName " +
                ibsGwaContext.getAwaArea().get("tlrName") +
                "\n");
        System.out.print("---debug : UserController.browsUser Awa tlrEName " +
                ibsGwaContext.getAwaArea().get("tlrEName") +
                "\n\n");

        //业务逻辑

        ibsGwaContext.getOutpArea().put("tlrInfo",userService.findList().toString());
        System.out.print("---debug : UserController.browsUser ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("addG")
    public IbsGwaContext addUserG(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.addUserG Tia \n");
        System.out.print("---debug : UserController.addUserG Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务逻辑
        UserG user = new UserG();
        user.setTlr((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString());
        user.setTlrBr(Integer.valueOf(ibsGwaContext.getBrDp()));
        user.setTlrCnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrName").toString());
        user.setTlrEnNm((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrEName").toString());
        user.setSignSts("F");
        user.setTlrSts("N");

        //Ioc 产品规则
        userGMapper.insert(user);

        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk",ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr",ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo",user.toString());

        System.out.print("---debug : UserController.addUserG Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

    @PostMapping("findG")
    public IbsGwaContext findUserG(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception
    {
        //以下开始为应用程序
        System.out.print("---debug : UserController.findUserG Tia \n");
        System.out.print("---debug : UserController.findUserG Tia ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

        //业务逻辑
        ibsGwaContext.getOutpArea().put("tlrInfo",((UserG)(userGMapper.selectByPrimaryKey((String)((HashMap)ibsGwaContext.getAwaArea()).get("tlrNo").toString()))).toString());



        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk",ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr",ibsGwaContext.getBrDp());

        System.out.print("---debug : UserController.addUserG Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }
}
