package com.hisun.VT;

public class VTOT7010_WS_OUT_DATA {
    int WS_NUM = 0;
    VTOT7010_WS_CCY_INFO[] WS_CCY_INFO = new VTOT7010_WS_CCY_INFO[20];
    public VTOT7010_WS_OUT_DATA() {
        for (int i=0;i<20;i++) WS_CCY_INFO[i] = new VTOT7010_WS_CCY_INFO();
    }
}
