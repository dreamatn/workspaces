package com.hisun.BA;

public class BAOT4030_WS_OUT_DATA {
    double WS_OUT_KHZ_AMT = 0;
    short WS_OUT_FEE_CNT = 0;
    BAOT4030_WS_OUT_FEE_ARRAY[] WS_OUT_FEE_ARRAY = new BAOT4030_WS_OUT_FEE_ARRAY[5];
    public BAOT4030_WS_OUT_DATA() {
        for (int i=0;i<5;i++) WS_OUT_FEE_ARRAY[i] = new BAOT4030_WS_OUT_FEE_ARRAY();
    }
}
