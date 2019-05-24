package com.hisun.BP;

public class BPOT5161_WS_OUT_DATA {
    String WS_EXR_TYPE = " ";
    int WS_EFF_DT = 0;
    int WS_EFF_TM = 0;
    int WS_IPT_DT = 0;
    int WS_IPT_TM = 0;
    int WS_CNT = 0;
    BPOT5161_WS_CCY_INFO[] WS_CCY_INFO = new BPOT5161_WS_CCY_INFO[20];
    public BPOT5161_WS_OUT_DATA() {
        for (int i=0;i<20;i++) WS_CCY_INFO[i] = new BPOT5161_WS_CCY_INFO();
    }
}
