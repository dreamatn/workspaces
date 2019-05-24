package com.hisun.BP;

public class BPZFSCPN_WS_DATA {
    BPZFSCPN_WS_KEY WS_KEY = new BPZFSCPN_WS_KEY();
    BPZFSCPN_WS_VAL[] WS_VAL = new BPZFSCPN_WS_VAL[20];
    public BPZFSCPN_WS_DATA() {
        for (int i=0;i<20;i++) WS_VAL[i] = new BPZFSCPN_WS_VAL();
    }
}
