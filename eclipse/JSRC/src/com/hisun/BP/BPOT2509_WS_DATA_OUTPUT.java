package com.hisun.BP;

public class BPOT2509_WS_DATA_OUTPUT {
    String WS_OUT_FROM_TLR = " ";
    int WS_OUT_DT_CNT = 0;
    BPOT2509_WS_OUT_ATM_INFO[] WS_OUT_ATM_INFO = new BPOT2509_WS_OUT_ATM_INFO[20];
    public BPOT2509_WS_DATA_OUTPUT() {
        for (int i=0;i<20;i++) WS_OUT_ATM_INFO[i] = new BPOT2509_WS_OUT_ATM_INFO();
    }
}
