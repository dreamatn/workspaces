package com.hisun.DC;

public class DCCSSPAC_OUTPUT {
    public String STD_AC = " ";
    public int STD_BR = 0;
    public char AC_TYPE = ' ';
    public int TOTAL_PAGE = 0;
    public int TOTAL_NUM = 0;
    public int CURR_PAGE = 0;
    public char LAST_PAGE = ' ';
    public int C_PAGE_ROW = 0;
    public short FREE_AC_NUM = 0;
    public DCCSSPAC_FREE_NO[] FREE_NO = new DCCSSPAC_FREE_NO[99];
    public DCCSSPAC_OUTPUT() {
        for (int i=0;i<99;i++) FREE_NO[i] = new DCCSSPAC_FREE_NO();
    }
}
