package com.hisun.DC;

public class DCCUABRW {
    public DCCUABRW_RC RC = new DCCUABRW_RC();
    public DCCUABRW_INP_DATA INP_DATA = new DCCUABRW_INP_DATA();
    public DCCUABRW_OUT_PAGE OUT_PAGE = new DCCUABRW_OUT_PAGE();
    public DCCUABRW_OUT_DATA[] OUT_DATA = new DCCUABRW_OUT_DATA[200];
    public DCCUABRW() {
        for (int i=0;i<200;i++) OUT_DATA[i] = new DCCUABRW_OUT_DATA();
    }
}
