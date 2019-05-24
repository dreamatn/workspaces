package com.hisun.BP;

public class BPOT2500_WS_CONF_TXT {
    BPOT2500_WS_CONF_INFO[] WS_CONF_INFO = new BPOT2500_WS_CONF_INFO[20];
    public BPOT2500_WS_CONF_TXT() {
        for (int i=0;i<20;i++) WS_CONF_INFO[i] = new BPOT2500_WS_CONF_INFO();
    }
}
