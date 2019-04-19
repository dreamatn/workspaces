package com.hisun.ibscore.app.br.controller;

import com.hisun.ibscore.app.br.domain.Br;
import com.hisun.ibscore.app.br.service.BrService;
import com.hisun.ibscore.core.annotation.IbsRequestBody;
import com.hisun.ibscore.core.annotation.IbsResponseBody;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller//这个注释说明是个SpringMvc
@IbsResponseBody//自定义注解
//@ResponseBody
@RequestMapping("/ibscore/bp/br") //访问的路径是/ibscore/bp/br
@Transactional(propagation = Propagation.REQUIRED) //使用事务管理
public class BrContorller {


    //Spring自动装配   会把你的Service层的类自动注入到下面定义的变量中
    //简单地说就是不用你自己new UserServiceFactory了  Spring帮助你new一个了
    @Autowired
    private BrService brService;


    //与类上面的RequestMapping连在一起用  也就是全路径就是“/ibscore/bp/user/add”
    @PostMapping("add")
    public IbsGwaContext addBr(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception {
        //业务逻辑
        Br br = new Br();
        br.setBr((Integer.parseInt((ibsGwaContext.getAwaArea()).get("br").toString())));
        br.setIbsAcBk((Integer.parseInt((ibsGwaContext.getAwaArea()).get("ibsAcBk").toString())));
        brService.add(br);
        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk", ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr", ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo", br.toString());
        System.out.print("---debug : UserController.addUser Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }


    @PostMapping("delete")
    public IbsGwaContext deleteBr(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception {
        //业务逻辑
        int  brnum  =Integer.parseInt((ibsGwaContext.getAwaArea().get("br").toString()));
        brService.delete(brnum);
        System.out.print(brnum);
        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk", ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr", ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo",brnum);
        System.out.print("---debug : UserController.addUser Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }
    @PostMapping("update")
    public IbsGwaContext updateBr(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception {
        //业务逻辑
        Br br = new Br();
        br.setBr((Integer.parseInt((ibsGwaContext.getAwaArea()).get("br").toString())));
        br.setIbsAcBk((Integer.parseInt((ibsGwaContext.getAwaArea()).get("ibsAcBk").toString())));
        brService.update(br);
        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk", ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr", ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo", br.toString());
        System.out.print("---debug : UserController.addUser Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }
    @PostMapping("find")
    public IbsGwaContext findBr(@IbsRequestBody IbsGwaContext ibsGwaContext) throws Exception {
        //业务逻辑
        Br  br =(Br) brService.findByKey(Integer.parseInt(ibsGwaContext.getAwaArea().get("br").toString()));
        //输出处理
        ibsGwaContext.getOutpArea().put("tlrBk", ibsGwaContext.getTrBank());
        ibsGwaContext.getOutpArea().put("tlrBr", ibsGwaContext.getBrDp());
        ibsGwaContext.getOutpArea().put("tlrInfo", br.toString());
        System.out.print("---debug : UserController.addUser Tia ibsGwaContext end " +
                ibsGwaContext.toString() +
                "\n\n");
        return ibsGwaContext;
    }

}
