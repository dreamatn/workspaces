package com.hisun.BP;

public class BPCSAGCA_CAL_DETAIL {
    public BPCSAGCA_MONTH[] MONTH = new BPCSAGCA_MONTH[12];
    public BPCSAGCA_CAL_DETAIL() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCSAGCA_MONTH();
    }
}
