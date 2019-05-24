package com.hisun.BP;

public class BPOT3029_WS_CONF_INFO {
    BPOT3029_WS_CONF_G[] WS_CONF_G = new BPOT3029_WS_CONF_G[200];
    String FILLER28 = " ";
    public BPOT3029_WS_CONF_INFO() {
        for (int i=0;i<200;i++) WS_CONF_G[i] = new BPOT3029_WS_CONF_G();
    }
}
