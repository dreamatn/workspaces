package com.hisun.TD;

public class TDZPRAM_WS_FMT {
    String WS_PROD_CD = " ";
    TDZPRAM_WS_OUT_INFO[] WS_OUT_INFO = new TDZPRAM_WS_OUT_INFO[5];
    int WS_TOTAL_PAGE = 0;
    int WS_TOTAL_NUM = 0;
    int WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    int WS_PAGE_ROW = 0;
    public TDZPRAM_WS_FMT() {
        for (int i=0;i<5;i++) WS_OUT_INFO[i] = new TDZPRAM_WS_OUT_INFO();
    }
}
