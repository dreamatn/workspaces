package com.hisun.DD;

public class DDZUBKVL_WS_TIR_INF {
    char WS_TIR_TYPE = ' ';
    char WS_AGSP_FLG = ' ';
    double WS_TAX_RATE = 0;
    char WS_INT_STS = ' ';
    char WS_CAL_INT_MTH = ' ';
    DDZUBKVL_WS_TIR_DETAIL[] WS_TIR_DETAIL = new DDZUBKVL_WS_TIR_DETAIL[5];
    public DDZUBKVL_WS_TIR_INF() {
        for (int i=0;i<5;i++) WS_TIR_DETAIL[i] = new DDZUBKVL_WS_TIR_DETAIL();
    }
}
