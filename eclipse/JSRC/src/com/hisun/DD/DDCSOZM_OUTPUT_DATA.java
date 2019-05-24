package com.hisun.DD;

public class DDCSOZM_OUTPUT_DATA {
    public int OZM_AC_CNT = 0;
    public DDCSOZM_OZM_AC_LIST[] OZM_AC_LIST = new DDCSOZM_OZM_AC_LIST[6];
    public int OZM_BV_CNT = 0;
    public DDCSOZM_OZM_BV_LIST[] OZM_BV_LIST = new DDCSOZM_OZM_BV_LIST[99];
    public DDCSOZM_OUTPUT_DATA() {
        for (int i=0;i<6;i++) OZM_AC_LIST[i] = new DDCSOZM_OZM_AC_LIST();
        for (int i=0;i<99;i++) OZM_BV_LIST[i] = new DDCSOZM_OZM_BV_LIST();
    }
}
