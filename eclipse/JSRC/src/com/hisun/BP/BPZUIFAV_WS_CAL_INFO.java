package com.hisun.BP;

public class BPZUIFAV_WS_CAL_INFO {
    BPZUIFAV_WS_CNT_INFO[] WS_CNT_INFO = new BPZUIFAV_WS_CNT_INFO[10];
    short WS_CNT = 0;
    short WS_CNT_FAV = 0;
    double WS_LAS_AMT = 0;
    double WS_NOW_AMT = 0;
    public BPZUIFAV_WS_CAL_INFO() {
        for (int i=0;i<10;i++) WS_CNT_INFO[i] = new BPZUIFAV_WS_CNT_INFO();
    }
}
