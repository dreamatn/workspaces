package com.hisun.BP;

public class BPOT2120_WS_CONF_INFO {
    BPOT2120_WS_CONF_G[] WS_CONF_G = new BPOT2120_WS_CONF_G[200];
    String FILLER48 = " ";
    public BPOT2120_WS_CONF_INFO() {
        for (int i=0;i<200;i++) WS_CONF_G[i] = new BPOT2120_WS_CONF_G();
    }
}
