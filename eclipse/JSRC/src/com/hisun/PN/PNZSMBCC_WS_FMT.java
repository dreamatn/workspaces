package com.hisun.PN;

public class PNZSMBCC_WS_FMT {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    double WS_SUM_AMT = 0;
    char PNZSMBCC_FILLER14 = 0X01;
    PNZSMBCC_WS_DATA[] WS_DATA = new PNZSMBCC_WS_DATA[24];
    public PNZSMBCC_WS_FMT() {
        for (int i=0;i<24;i++) WS_DATA[i] = new PNZSMBCC_WS_DATA();
    }
}
