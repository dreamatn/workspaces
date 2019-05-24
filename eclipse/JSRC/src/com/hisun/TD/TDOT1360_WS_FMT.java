package com.hisun.TD;

public class TDOT1360_WS_FMT {
    String WS_AC_NO = " ";
    char WS_END_FLG = ' ';
    short WS_PAGE_ROW = 0;
    int WS_PAGE_NUM = 0;
    short WS_TOTAL_PAGE = 0;
    short WS_TOTAL_NUM = 0;
    TDOT1360_WS_DATA[] WS_DATA = new TDOT1360_WS_DATA[18];
    public TDOT1360_WS_FMT() {
        for (int i=0;i<18;i++) WS_DATA[i] = new TDOT1360_WS_DATA();
    }
}
