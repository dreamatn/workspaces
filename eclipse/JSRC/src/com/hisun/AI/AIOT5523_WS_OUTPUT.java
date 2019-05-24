package com.hisun.AI;

public class AIOT5523_WS_OUTPUT {
    int WS_C_DATE = 0;
    long WS_C_JRN_NO = 0;
    String WS_TLR_ID = " ";
    String WS_SUS_TELLER = " ";
    AIOT5523_WS_BSAIC_INFO[] WS_BSAIC_INFO = new AIOT5523_WS_BSAIC_INFO[15];
    public AIOT5523_WS_OUTPUT() {
        for (int i=0;i<15;i++) WS_BSAIC_INFO[i] = new AIOT5523_WS_BSAIC_INFO();
    }
}
