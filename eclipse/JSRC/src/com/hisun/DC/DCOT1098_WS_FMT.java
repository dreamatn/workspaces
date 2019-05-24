package com.hisun.DC;

public class DCOT1098_WS_FMT {
    DCOT1098_WS_DATA[] WS_DATA = new DCOT1098_WS_DATA[5];
    int WS_TOTAL_PAGE = 0;
    int WS_TOTAL_NUM = 0;
    int WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    int WS_PAGE_ROW = 0;
    String WS_PROD_NO = " ";
    public DCOT1098_WS_FMT() {
        for (int i=0;i<5;i++) WS_DATA[i] = new DCOT1098_WS_DATA();
    }
}
