package com.hisun.BP;

public class BPZSCHNB_WS_CHNTB_INFO {
    BPZSCHNB_WS_CHNTB_DATA[] WS_CHNTB_DATA = new BPZSCHNB_WS_CHNTB_DATA[100];
    public BPZSCHNB_WS_CHNTB_INFO() {
        for (int i=0;i<100;i++) WS_CHNTB_DATA[i] = new BPZSCHNB_WS_CHNTB_DATA();
    }
}
