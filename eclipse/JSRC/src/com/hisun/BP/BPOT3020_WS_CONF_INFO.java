package com.hisun.BP;

public class BPOT3020_WS_CONF_INFO {
    BPOT3020_WS_CONF_G[] WS_CONF_G = new BPOT3020_WS_CONF_G[200];
    String FILLER41 = " ";
    public BPOT3020_WS_CONF_INFO() {
        for (int i=0;i<200;i++) WS_CONF_G[i] = new BPOT3020_WS_CONF_G();
    }
}
