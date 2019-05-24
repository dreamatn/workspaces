package com.hisun.BP;

public class BPOT2129_WS_CONF_INFO {
    BPOT2129_WS_CONF_G[] WS_CONF_G = new BPOT2129_WS_CONF_G[200];
    String BPOT2129_FILLER12 = " ";
    public BPOT2129_WS_CONF_INFO() {
        for (int i=0;i<200;i++) WS_CONF_G[i] = new BPOT2129_WS_CONF_G();
    }
}
