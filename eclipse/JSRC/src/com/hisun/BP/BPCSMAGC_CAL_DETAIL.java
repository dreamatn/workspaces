package com.hisun.BP;

public class BPCSMAGC_CAL_DETAIL {
    public BPCSMAGC_MONTH[] MONTH = new BPCSMAGC_MONTH[12];
    public BPCSMAGC_CAL_DETAIL() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCSMAGC_MONTH();
    }
}
