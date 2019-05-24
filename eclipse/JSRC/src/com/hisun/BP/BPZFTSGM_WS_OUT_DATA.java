package com.hisun.BP;

public class BPZFTSGM_WS_OUT_DATA {
    int WS_OUT_CNT = 0;
    int WS_OUT_BRANCH = 0;
    int WS_OUT_DATE = 0;
    int WS_OUT_TIME = 0;
    BPZFTSGM_WS_OUT_ARRARY[] WS_OUT_ARRARY = new BPZFTSGM_WS_OUT_ARRARY[50];
    public BPZFTSGM_WS_OUT_DATA() {
        for (int i=0;i<50;i++) WS_OUT_ARRARY[i] = new BPZFTSGM_WS_OUT_ARRARY();
    }
}
