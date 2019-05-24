package com.hisun.TD;

public class TDOT5710_WS_FMT {
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    short WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    short WS_PAGE_ROW = 0;
    TDOT5710_WS_DATA[] WS_DATA = new TDOT5710_WS_DATA[15];
    public TDOT5710_WS_FMT() {
        for (int i=0;i<15;i++) WS_DATA[i] = new TDOT5710_WS_DATA();
    }
}
