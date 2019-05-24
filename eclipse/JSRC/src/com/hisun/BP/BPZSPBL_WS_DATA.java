package com.hisun.BP;

public class BPZSPBL_WS_DATA {
    String WS_ITM_PNT = " ";
    String WS_ITM_NO = " ";
    int WS_ITM_SEQ = 0;
    BPZSPBL_WS_AMT_PNT[] WS_AMT_PNT = new BPZSPBL_WS_AMT_PNT[76];
    String WS_REMARK = " ";
    public BPZSPBL_WS_DATA() {
        for (int i=0;i<76;i++) WS_AMT_PNT[i] = new BPZSPBL_WS_AMT_PNT();
    }
}
