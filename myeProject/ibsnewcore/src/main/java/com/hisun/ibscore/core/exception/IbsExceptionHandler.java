package com.hisun.ibscore.core.exception;

import com.hisun.ibscore.core.annotation.IbsResponseBody;
import com.hisun.ibscore.core.dto.IbsGwaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@ControllerAdvice
@IbsResponseBody
public class IbsExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IbsGwaContext ibsGwaContext;

    @ExceptionHandler(value = Exception.class)
    public IbsGwaContext Handler(Exception e){
        ibsGwaContext = (IbsGwaContext) request.getAttribute("ibsGwaContext");
        if (ibsGwaContext == null) {
            ibsGwaContext = new IbsGwaContext();
        }
        System.out.print("---debug : IbsExceptionHandler.Handler \n\n");
        System.out.print("---debug : IbsExceptionHandler.Handler e.getLocalizedMessage " +
                e.getLocalizedMessage() +
                "\n\n");
        System.out.print("---debug : IbsExceptionHandler.Handler e.getMessage " +
                e.getMessage() +
                "\n\n");
        System.out.print("---debug : IbsExceptionHandler.Handler e.getCause " +
                e.getCause() +
                "\n\n");
//        System.out.print("---debug : IbsExceptionHandler.Handler e.getStackTrace " +
//                e.getStackTrace() +
//                "\n\n");
//        System.out.print("---debug : IbsExceptionHandler.Handler e.getSuppressed " +
//                e.getSuppressed() +
//                "\n\n");
        System.out.print("---debug : IbsExceptionHandler.Handler e.getClass " +
                e.getClass() +
                "\n\n");

        StackTraceElement[] traceElements = e.getStackTrace();
        ArrayList<String> errTrace = new ArrayList <String>();
//        errTrace.add(e.getMessage());
        for (StackTraceElement traceElement : traceElements){
            if(traceElement.toString().contains("com.hisun")) {
                errTrace.add(traceElement.toString());
            }
        }


        if(e instanceof IbsException){
            ibsGwaContext.setMsgType(((IbsException) e).getMsgType());
            ibsGwaContext.setMsgId(((IbsException) e).getMsgId());
            errTrace.add(0,((IbsException) e).getMsgDesc());
            ibsGwaContext.getOutpArea().put("errDesc",errTrace);
            return ibsGwaContext;
        }else{
            ibsGwaContext.setMsgType("E");
            ibsGwaContext.setMsgId("SC6001");
            errTrace.add(0,e.getMessage());
            ibsGwaContext.getOutpArea().put("errDesc",errTrace);
            return ibsGwaContext;
        }
    }
}
