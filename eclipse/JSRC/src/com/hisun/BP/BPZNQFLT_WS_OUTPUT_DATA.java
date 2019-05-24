package com.hisun.BP;

public class BPZNQFLT_WS_OUTPUT_DATA {
    BPZNQFLT_WS_BPCD_DAT[] WS_BPCD_DAT = new BPZNQFLT_WS_BPCD_DAT[10];
    String NAME = " ";
    String ENG_NAME = " ";
    public BPZNQFLT_WS_OUTPUT_DATA() {
        for (int i=0;i<10;i++) WS_BPCD_DAT[i] = new BPZNQFLT_WS_BPCD_DAT();
    }
}
