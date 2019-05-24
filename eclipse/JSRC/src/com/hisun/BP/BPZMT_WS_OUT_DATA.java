package com.hisun.BP;

public class BPZMT_WS_OUT_DATA {
    int WS_X_BR = 0;
    String WS_X_EXR_TYPE = " ";
    BPZMT_WS_X_CCY_INFO[] WS_X_CCY_INFO = new BPZMT_WS_X_CCY_INFO[20];
    public BPZMT_WS_OUT_DATA() {
        for (int i=0;i<20;i++) WS_X_CCY_INFO[i] = new BPZMT_WS_X_CCY_INFO();
    }
}
