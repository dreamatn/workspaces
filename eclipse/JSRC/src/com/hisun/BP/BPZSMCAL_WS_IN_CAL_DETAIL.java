package com.hisun.BP;

public class BPZSMCAL_WS_IN_CAL_DETAIL {
    BPZSMCAL_WS_IN_MONTH[] WS_IN_MONTH = new BPZSMCAL_WS_IN_MONTH[12];
    int WS_IN_LST_DATE = 0;
    String WS_IN_LST_TLT = " ";
    public BPZSMCAL_WS_IN_CAL_DETAIL() {
        for (int i=0;i<12;i++) WS_IN_MONTH[i] = new BPZSMCAL_WS_IN_MONTH();
    }
}
