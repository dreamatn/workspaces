package com.hisun.BP;

public class BPZSALCS_WS_CCY_INFO {
    BPZSALCS_WS_CCY_REC[] WS_CCY_REC = new BPZSALCS_WS_CCY_REC[20];
    public BPZSALCS_WS_CCY_INFO() {
        for (int i=0;i<20;i++) WS_CCY_REC[i] = new BPZSALCS_WS_CCY_REC();
    }
}
