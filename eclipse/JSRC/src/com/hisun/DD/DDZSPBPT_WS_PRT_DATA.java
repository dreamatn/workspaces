package com.hisun.DD;

public class DDZSPBPT_WS_PRT_DATA {
    String WS_AC = " ";
    int WS_TOT_PAGE = 0;
    int WS_CURR_ROW = 0;
    int WS_TOT_UPT_ROW = 0;
    int WS_CNT = 0;
    int WS_PSBK_PAGE = 0;
    int WS_PSBK_ROW = 0;
    char WS_END_FLG = ' ';
    DDZSPBPT_WS_ARRY[] WS_ARRY = new DDZSPBPT_WS_ARRY[25];
    public DDZSPBPT_WS_PRT_DATA() {
        for (int i=0;i<25;i++) WS_ARRY[i] = new DDZSPBPT_WS_ARRY();
    }
}
