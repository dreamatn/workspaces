package com.hisun.BP;

public class BPZSIHSA_WS_OUTPUT_DATA {
    short WS_REC_NUM = 0;
    BPZSIHSA_WS_TS_DATA[] WS_TS_DATA = new BPZSIHSA_WS_TS_DATA[186];
    public BPZSIHSA_WS_OUTPUT_DATA() {
        for (int i=0;i<186;i++) WS_TS_DATA[i] = new BPZSIHSA_WS_TS_DATA();
    }
}
