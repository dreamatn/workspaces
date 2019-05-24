package com.hisun.CI;

public class CICSGRS_DATA {
    public String CI_NO = " ";
    public CICSGRS_MULTI_DATA[] MULTI_DATA = new CICSGRS_MULTI_DATA[99];
    public CICSGRS_DATA() {
        for (int i=0;i<99;i++) MULTI_DATA[i] = new CICSGRS_MULTI_DATA();
    }
}
