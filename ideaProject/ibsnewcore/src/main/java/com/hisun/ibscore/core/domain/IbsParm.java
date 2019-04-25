package com.hisun.ibscore.core.domain;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletRequest;

public class IbsParm {
    private String  parmKey;
    private String  desc;
    private String  val;
//    static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();

    public String getParmKey() {
        return parmKey;
    }

    public void setParmKey(String parmKey) {
        this.parmKey = parmKey;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public IbsParm() {
    }

    public IbsParm(String parmKey, String desc, String val) {
        this.parmKey = parmKey;
        this.desc = desc;
        this.val = val;
    }

    @Override
    public String toString() {
        return "IbsParm{" +
                "parmKey='" + parmKey + '\'' +
                ", desc='" + desc + '\'' +
                ", val='" + val + '\'' +
                '}';
    }

    public static IbsParm getIbsParm(String ibsParmType,String ibsParmKey, ServletRequest servletRequest) {
        IbsMemoryTree ibsMemoryTree = (IbsMemoryTree) servletRequest.getServletContext().getAttribute("ibsMemoryTree");
        return (IbsParm) ibsMemoryTree.getMemoryTree().get(ibsParmType).get(ibsParmKey);
    }
}
