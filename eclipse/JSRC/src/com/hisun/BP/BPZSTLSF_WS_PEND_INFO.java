package com.hisun.BP;

public class BPZSTLSF_WS_PEND_INFO {
    BPZSTLSF_WS_PEND_DATA[] WS_PEND_DATA = new BPZSTLSF_WS_PEND_DATA[200];
    public BPZSTLSF_WS_PEND_INFO() {
        for (int i=0;i<200;i++) WS_PEND_DATA[i] = new BPZSTLSF_WS_PEND_DATA();
    }
}
