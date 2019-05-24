package com.hisun.GD;

public class GDZSBPAC_WS_OUT_DATA {
    GDZSBPAC_WS_DR_AMT_ARRAY[] WS_DR_AMT_ARRAY = new GDZSBPAC_WS_DR_AMT_ARRAY[5];
    double DK_AMT = 0;
    char INT_TO_AI_FLG = ' ';
    char ST_TO_AI_FLG = ' ';
    char PAYING_INT_FLG = ' ';
    String CNTRT_NO = " ";
    double DRW_AMT = 0;
    public GDZSBPAC_WS_OUT_DATA() {
        for (int i=0;i<5;i++) WS_DR_AMT_ARRAY[i] = new GDZSBPAC_WS_DR_AMT_ARRAY();
    }
}
