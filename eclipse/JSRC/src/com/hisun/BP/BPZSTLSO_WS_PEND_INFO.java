package com.hisun.BP;

public class BPZSTLSO_WS_PEND_INFO {
    public BPZSTLSO_WS_PEND_DATA[] WS_PEND_DATA = new BPZSTLSO_WS_PEND_DATA[200];
    public BPZSTLSO_WS_PEND_INFO() {
        for (int i=0;i<200;i++) WS_PEND_DATA[i] = new BPZSTLSO_WS_PEND_DATA();
    }
}
