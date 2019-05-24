package com.hisun.BP;

public class BPZSENTY_WS_VAL {
    String WS_MOD_NO_AC = " ";
    String WS_MOD_NAME = " ";
    char WS_MOD_TYP = ' ';
    BPZSENTY_WS_EVENT[] WS_EVENT = new BPZSENTY_WS_EVENT[60];
    public BPZSENTY_WS_VAL() {
        for (int i=0;i<60;i++) WS_EVENT[i] = new BPZSENTY_WS_EVENT();
    }
}
