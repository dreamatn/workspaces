package com.hisun.PN;

public class PNZSMDFT_WS_FMT {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    double WS_SUM_AMT = 0;
    char PNZSMDFT_FILLER14 = 0X01;
    PNZSMDFT_WS_DATA[] WS_DATA = new PNZSMDFT_WS_DATA[24];
    public PNZSMDFT_WS_FMT() {
        for (int i=0;i<24;i++) WS_DATA[i] = new PNZSMDFT_WS_DATA();
    }
}
