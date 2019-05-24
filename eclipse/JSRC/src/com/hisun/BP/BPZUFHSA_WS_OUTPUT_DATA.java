package com.hisun.BP;

public class BPZUFHSA_WS_OUTPUT_DATA {
    short WS_REC_NUM = 0;
    BPZUFHSA_WS_TS_DATA[] WS_TS_DATA = new BPZUFHSA_WS_TS_DATA[186];
    public BPZUFHSA_WS_OUTPUT_DATA() {
        for (int i=0;i<186;i++) WS_TS_DATA[i] = new BPZUFHSA_WS_TS_DATA();
    }
}
