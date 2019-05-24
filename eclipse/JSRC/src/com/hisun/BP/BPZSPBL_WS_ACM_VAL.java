package com.hisun.BP;

public class BPZSPBL_WS_ACM_VAL {
    String WS_MOD_NO_AC = " ";
    String WS_MOD_NAME = " ";
    BPZSPBL_WS_EVENT[] WS_EVENT = new BPZSPBL_WS_EVENT[60];
    public BPZSPBL_WS_ACM_VAL() {
        for (int i=0;i<60;i++) WS_EVENT[i] = new BPZSPBL_WS_EVENT();
    }
}
