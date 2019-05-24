package com.hisun.DC;

public class DCCUSPAC_OUTPUT {
    public char AC_TYPE = ' ';
    public String STD_AC = " ";
    public short FREE_AC_NUM = 0;
    public DCCUSPAC_FREE_NO[] FREE_NO = new DCCUSPAC_FREE_NO[99];
    public DCCUSPAC_OUTPUT() {
        for (int i=0;i<99;i++) FREE_NO[i] = new DCCUSPAC_FREE_NO();
    }
}
