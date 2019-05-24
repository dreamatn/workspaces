package com.hisun.BP;

public class BPZRFLT_WS_OUTPUT_DATA {
    BPZRFLT_WS_DATA[] WS_DATA = new BPZRFLT_WS_DATA[10];
    public BPZRFLT_WS_OUTPUT_DATA() {
        for (int i=0;i<10;i++) WS_DATA[i] = new BPZRFLT_WS_DATA();
    }
}
