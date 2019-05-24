package com.hisun.DD;

public class DDZSLNPY_WS_OUT_LPAY_INF {
    char M_FUNC = ' ';
    String M_LNCNTR_NO = " ";
    short M_LNTX_SEQ = 0;
    short M_NUM = 0;
    char M_INR_FLG = ' ';
    char M_LNN_FLG = ' ';
    DDZSLNPY_WS_OUT_LPAY[] WS_OUT_LPAY = new DDZSLNPY_WS_OUT_LPAY[10];
    public DDZSLNPY_WS_OUT_LPAY_INF() {
        for (int i=0;i<10;i++) WS_OUT_LPAY[i] = new DDZSLNPY_WS_OUT_LPAY();
    }
}
