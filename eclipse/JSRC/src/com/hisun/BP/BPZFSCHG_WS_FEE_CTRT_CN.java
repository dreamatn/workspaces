package com.hisun.BP;

public class BPZFSCHG_WS_FEE_CTRT_CN {
    char WS_PFEE_CTRT_FLG = ' ';
    String WS_PFEE_FEE_CTRT = " ";
    BPZFSCHG_WS_FEE_CTRT_TABLE[] WS_FEE_CTRT_TABLE = new BPZFSCHG_WS_FEE_CTRT_TABLE[20];
    public BPZFSCHG_WS_FEE_CTRT_CN() {
        for (int i=0;i<20;i++) WS_FEE_CTRT_TABLE[i] = new BPZFSCHG_WS_FEE_CTRT_TABLE();
    }
}
