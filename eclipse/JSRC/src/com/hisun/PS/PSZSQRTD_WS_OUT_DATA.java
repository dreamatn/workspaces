package com.hisun.PS;

public class PSZSQRTD_WS_OUT_DATA {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_CURR_PAGE_ROW = 0;
    PSZSQRTD_WS_DATA[] WS_DATA = new PSZSQRTD_WS_DATA[10];
    public PSZSQRTD_WS_OUT_DATA() {
        for (int i=0;i<10;i++) WS_DATA[i] = new PSZSQRTD_WS_DATA();
    }
}
