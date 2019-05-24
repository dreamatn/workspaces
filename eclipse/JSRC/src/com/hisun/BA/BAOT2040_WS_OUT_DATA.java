package com.hisun.BA;

public class BAOT2040_WS_OUT_DATA {
    double WS_OUT_KHZ_AMT = 0;
    short WS_OUT_FEE_CNT = 0;
    BAOT2040_WS_OUT_FEE_ARRAY[] WS_OUT_FEE_ARRAY = new BAOT2040_WS_OUT_FEE_ARRAY[5];
    public BAOT2040_WS_OUT_DATA() {
        for (int i=0;i<5;i++) WS_OUT_FEE_ARRAY[i] = new BAOT2040_WS_OUT_FEE_ARRAY();
    }
}
