package com.hisun.BP;

public class BPZSAGCA_WS_OD_CAL_DETAIL {
    BPZSAGCA_WS_OD_MONTH[] WS_OD_MONTH = new BPZSAGCA_WS_OD_MONTH[12];
    public BPZSAGCA_WS_OD_CAL_DETAIL() {
        for (int i=0;i<12;i++) WS_OD_MONTH[i] = new BPZSAGCA_WS_OD_MONTH();
    }
}
