package com.hisun.BP;

public class BPZFSCHF_WS_FEE_CTRT_CN {
    char WS_PFEE_CTRT_FLG = ' ';
    String WS_PFEE_FEE_CTRT = " ";
    BPZFSCHF_WS_FEE_CTRT_TABLE[] WS_FEE_CTRT_TABLE = new BPZFSCHF_WS_FEE_CTRT_TABLE[20];
    public BPZFSCHF_WS_FEE_CTRT_CN() {
        for (int i=0;i<20;i++) WS_FEE_CTRT_TABLE[i] = new BPZFSCHF_WS_FEE_CTRT_TABLE();
    }
}
