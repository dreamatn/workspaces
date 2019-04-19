package com.hisun.ibscore.core.service.impl;

import com.hisun.ibscore.app.bp.constant.BpMsgConstant;
import com.hisun.ibscore.core.constant.IbsMsgConstant;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import com.hisun.ibscore.core.exception.IbsException;
import com.hisun.ibscore.core.service.IbsExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IbsExceptionServiceImpl implements IbsExceptionService {

    private IbsGwaContext ibsGwaContext;

    @Autowired
    public void setIbsGwaContext(IbsGwaContext ibsGwaContext) {
        this.ibsGwaContext = ibsGwaContext;
    }
//    public IbsExceptionServiceImpl(IbsGwaContext ibsGwaContext) {
//        this.ibsGwaContext = ibsGwaContext;
//    }

    @Override
    public void process(String msgId) throws Exception {

        //test
        String msgType = "N";
        String msgDesc = "";
        if((IbsMsgConstant.M_SC_TIA_IS_NULL).equals(msgId)){
            msgType = "E";
            msgDesc = "M_SC_TIA_IS_NULL";
        }
        if((BpMsgConstant.M_BP_USER_PASSWORD_MUST_INPUT).equals(msgId)){
            msgType = "E";
            msgDesc = "M_BP_USER_PASSWORD_MUST_INPUT";
        }
        if((BpMsgConstant.M_BP_USER_NAME_NOT_INPUT).equals(msgId)){
            msgType = "A";
            msgDesc = "M_BP_USER_NAME_NOT_INPUT";
        }
        if((BpMsgConstant.M_BP_USER_ENAME_NOT_INPUT).equals(msgId)){
            msgType = "A";
            msgDesc = "M_BP_USER_ENAME_NOT_INPUT";
        }
        if(("E").equals(msgType)) {
            throw new IbsException(msgType, msgId, msgDesc);
        } else if (("A").equals(msgType)){
            setIbsGwaContext( (IbsGwaContext) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getAttribute("ibsGwaContext"));
            saveAuthArea(msgId,msgDesc);
        } else if (("W").equals(msgType)){
            setIbsGwaContext( (IbsGwaContext) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest().getAttribute("ibsGwaContext"));
            saveAuthArea(msgId,msgDesc);
        } else {
            throw new IbsException("E", "ERROR msgType", "msgType is " + msgType);
        }

    }

    @Override
    public void saveAuthArea(String msgId,String msgDesc) throws Exception {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes()).getRequest();
//        HashMap authInfo = new HashMap();
//        authInfo.put(msgId,msgDesc);
//        ibsGwaContext = (IbsGwaContext) ((ServletRequestAttributes) RequestContextHolder
//                .getRequestAttributes()).getRequest().getAttribute("ibsGwaContext");

        System.out.print("---debug : IbsExceptionServiceImpl.saveAuthArea ibsGwaContext " +
                ibsGwaContext.toString() +
                "\n\n");

//        ArrayList <HashMap> authArea = null;
        HashMap authArea = null;
        if (ibsGwaContext.getOutpArea().get("authArea") == null){
            authArea = new HashMap();
            ibsGwaContext.getOutpArea().put("authArea",authArea);
        } else {
            authArea = (HashMap) ibsGwaContext.getOutpArea().get("authArea");
        }
        ibsGwaContext.setMsgType("A");
        authArea.put(msgId,msgDesc);
    }
}
