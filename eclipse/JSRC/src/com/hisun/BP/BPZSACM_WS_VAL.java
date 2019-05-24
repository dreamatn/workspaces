package com.hisun.BP;

public class BPZSACM_WS_VAL {
    String WS_MOD_NO = " ";
    String WS_MOD_NAME = " ";
    char WS_MOD_TYP = ' ';
    BPZSACM_WS_EVENT[] WS_EVENT = new BPZSACM_WS_EVENT[60];
    public BPZSACM_WS_VAL() {
        for (int i=0;i<60;i++) WS_EVENT[i] = new BPZSACM_WS_EVENT();
    }
}
