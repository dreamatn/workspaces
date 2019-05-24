package com.hisun.BP;

public class BPOT5163_WS_OUT_DATA {
    String WS_X_EXR_TYPE = " ";
    int WS_X_EFF_DT = 0;
    int WS_X_EFF_TM = 0;
    int WS_X_IPT_DT = 0;
    int WS_X_IPT_TM = 0;
    BPOT5163_WS_X_CCY_INFO[] WS_X_CCY_INFO = new BPOT5163_WS_X_CCY_INFO[7];
    public BPOT5163_WS_OUT_DATA() {
        for (int i=0;i<7;i++) WS_X_CCY_INFO[i] = new BPOT5163_WS_X_CCY_INFO();
    }
}
