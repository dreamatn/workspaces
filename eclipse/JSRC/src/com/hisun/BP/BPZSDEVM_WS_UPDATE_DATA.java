package com.hisun.BP;

public class BPZSDEVM_WS_UPDATE_DATA {
    char WS_SDEVM_TYPE = ' ';
    int WS_SDEVM_BR = 0;
    String WS_SDEVM_CCY = " ";
    String WS_SDEVM_BASE_TYP = " ";
    String WS_SDEVM_TENOR = " ";
    char WS_SDEVM_FMT = ' ';
    int WS_SDEVM_CNT = 0;
    BPZSDEVM_WS_SDEVM_DATA[] WS_SDEVM_DATA = new BPZSDEVM_WS_SDEVM_DATA[10];
    public BPZSDEVM_WS_UPDATE_DATA() {
        for (int i=0;i<10;i++) WS_SDEVM_DATA[i] = new BPZSDEVM_WS_SDEVM_DATA();
    }
}
