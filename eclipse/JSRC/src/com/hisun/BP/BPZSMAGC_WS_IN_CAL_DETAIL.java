package com.hisun.BP;

public class BPZSMAGC_WS_IN_CAL_DETAIL {
    BPZSMAGC_WS_IN_MONTH[] WS_IN_MONTH = new BPZSMAGC_WS_IN_MONTH[12];
    public BPZSMAGC_WS_IN_CAL_DETAIL() {
        for (int i=0;i<12;i++) WS_IN_MONTH[i] = new BPZSMAGC_WS_IN_MONTH();
    }
}
