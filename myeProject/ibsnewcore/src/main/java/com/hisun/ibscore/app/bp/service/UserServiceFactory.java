package com.hisun.ibscore.app.bp.service;

import com.hisun.ibscore.app.bp.service.impl.UserAtmRuleImpl;
import com.hisun.ibscore.app.bp.service.impl.UserNormalRuleImpl;
import com.hisun.ibscore.app.bp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserServiceFactory {

    @Autowired
    private ApplicationContext applicationContext;

    public UserRule getUserRule(String UserType){
        switch (UserType){
            case "N":
                return applicationContext.getBean(UserNormalRuleImpl.class);
            case "ATM":
                return applicationContext.getBean(UserAtmRuleImpl.class);
            default:
                return null;
        }
    }

    public UserService getUserService(String UserType){
        switch (UserType){
            case "N":
                return applicationContext.getBean(UserServiceImpl.class);
            case "ATM":
                return applicationContext.getBean(UserServiceImpl.class);
            default:
                return null;
        }
    }


}
