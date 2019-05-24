package com.hisun.BP;

public class BPZSGBAL_WS_OUTPUT_HIS {
    short WS_CNT_HIS = 0;
    BPZSGBAL_WS_BODY_HIS[] WS_BODY_HIS = new BPZSGBAL_WS_BODY_HIS[30];
    public BPZSGBAL_WS_OUTPUT_HIS() {
        for (int i=0;i<30;i++) WS_BODY_HIS[i] = new BPZSGBAL_WS_BODY_HIS();
    }
}
