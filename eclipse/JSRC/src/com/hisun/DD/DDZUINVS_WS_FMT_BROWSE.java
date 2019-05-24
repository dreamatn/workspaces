package com.hisun.DD;

public class DDZUINVS_WS_FMT_BROWSE {
    int WS_FMT_B_TOTAL_PAGE = 0;
    int WS_FMT_B_TOTAL_NUM = 0;
    int WS_FMT_B_CURR_PAGE = 0;
    char WS_FMT_B_LAST_PAGE = ' ';
    int WS_FMT_B_PAGE_ROW = 0;
    String WS_FMT_B_P_AC = " ";
    String WS_FMT_B_P_AC_NM = " ";
    char DDZUINVS_FILLER10 = 0X02;
    char WS_FMT_B_CINT_FLG = ' ';
    DDZUINVS_WS_FMT_B_DATA[] WS_FMT_B_DATA = new DDZUINVS_WS_FMT_B_DATA[5];
    public DDZUINVS_WS_FMT_BROWSE() {
        for (int i=0;i<5;i++) WS_FMT_B_DATA[i] = new DDZUINVS_WS_FMT_B_DATA();
    }
}
