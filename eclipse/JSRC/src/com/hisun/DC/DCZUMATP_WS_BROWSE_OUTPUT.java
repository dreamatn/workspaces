package com.hisun.DC;

public class DCZUMATP_WS_BROWSE_OUTPUT {
    int WS_TOTAL_PAGE = 0;
    int WS_TOTAL_NUM = 0;
    int WS_CURR_PAGE = 0;
    char WS_LAST_PAGE = ' ';
    int WS_PAGE_ROW = 0;
    DCZUMATP_WS_FMT_DATA[] WS_FMT_DATA = new DCZUMATP_WS_FMT_DATA[25];
    public DCZUMATP_WS_BROWSE_OUTPUT() {
        for (int i=0;i<25;i++) WS_FMT_DATA[i] = new DCZUMATP_WS_FMT_DATA();
    }
}
