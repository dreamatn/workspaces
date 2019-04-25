package com.hisun.ibscore.app.bp.service.impl;
import com.hisun.ibscore.app.bp.domain.User;
import com.hisun.ibscore.app.bp.service.UserRule;
import org.springframework.stereotype.Service;

@Service
public class UserAtmRuleImpl implements UserRule {
    @Override
    public void check(User user) throws Exception {
        if (("").equals(user.getTlrCnNm())){
            System.out.print("---debug : UserAtmRuleImpl.check name can space" +
                    "\n\n");
        }
    }
}
