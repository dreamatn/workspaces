package com.hisun.AI;

public class AIOT8528_WS_FMT {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_CURR_PAGE_ROW = 0;
    AIOT8528_WS_DATA[] WS_DATA = new AIOT8528_WS_DATA[12];
    public AIOT8528_WS_FMT() {
        for (int i=0;i<12;i++) WS_DATA[i] = new AIOT8528_WS_DATA();
    }
}
