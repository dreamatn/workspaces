package com.hisun.BP;

public class BPZSPBL_WS_OUTPUT_DATA {
    short WS_OUTPUT_CNT = 0;
    BPZSPBL_WS_DATA[] WS_DATA = new BPZSPBL_WS_DATA[70];
    String WS_UPD_TEL = " ";
    int WS_UPD_DATE = 0;
    int WS_UPD_TIME = 0;
    String WS_SUP_TEL1 = " ";
    String WS_SUP_TEL2 = " ";
    public BPZSPBL_WS_OUTPUT_DATA() {
        for (int i=0;i<70;i++) WS_DATA[i] = new BPZSPBL_WS_DATA();
    }
}
