package com.hisun.BP;

public class BPCSCCAL_CAL_DETAIL {
    public BPCSCCAL_MONTH[] MONTH = new BPCSCCAL_MONTH[12];
    public int LST_DATE = 0;
    public String LST_TLT = " ";
    public BPCSCCAL_CAL_DETAIL() {
        for (int i=0;i<12;i++) MONTH[i] = new BPCSCCAL_MONTH();
    }
}
