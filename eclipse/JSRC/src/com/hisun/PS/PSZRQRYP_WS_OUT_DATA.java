package com.hisun.PS;

public class PSZRQRYP_WS_OUT_DATA {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    PSZRQRYP_WS_DATA[] WS_DATA = new PSZRQRYP_WS_DATA[10];
    public PSZRQRYP_WS_OUT_DATA() {
        for (int i=0;i<10;i++) WS_DATA[i] = new PSZRQRYP_WS_DATA();
    }
}
