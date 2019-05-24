package com.hisun.BP;

public class BPOT2071_WS_OUTPUT_DETAIL {
    int WS_TR_DT = 0;
    long WS_JRNNO = 0;
    BPOT2071_WS_DETAIL[] WS_DETAIL = new BPOT2071_WS_DETAIL[200];
    public BPOT2071_WS_OUTPUT_DETAIL() {
        for (int i=0;i<200;i++) WS_DETAIL[i] = new BPOT2071_WS_DETAIL();
    }
}
