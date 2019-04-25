package com.hisun.ibscore.app.bp.service.impl;
import com.hisun.ibscore.app.bp.constant.BpMsgConstant;
import com.hisun.ibscore.app.bp.domain.User;
import com.hisun.ibscore.app.bp.service.UserRule;
import com.hisun.ibscore.core.utils.IbsTool;
import org.springframework.stereotype.Service;

@Service
public class UserNormalRuleImpl implements UserRule {
    @Override
    public void check(User user) throws Exception {
        if (("").equals(user.getTlrCnNm())){
            System.out.print("---debug : UserNormalRuleImpl.check " +
                    BpMsgConstant.M_BP_USER_NAME_NOT_INPUT +
                    "\n\n");
            IbsTool.IbsExcp(BpMsgConstant.M_BP_USER_NAME_NOT_INPUT);
        }
    }
}
