package com.hisun.BP;

public class BPZSGBAL_WS_OUTPUT_TOD {
    short WS_CNT_TOD = 0;
    BPZSGBAL_WS_BODY_TOD[] WS_BODY_TOD = new BPZSGBAL_WS_BODY_TOD[30];
    public BPZSGBAL_WS_OUTPUT_TOD() {
        for (int i=0;i<30;i++) WS_BODY_TOD[i] = new BPZSGBAL_WS_BODY_TOD();
    }
}
