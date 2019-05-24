package com.hisun.BP;

public class BPZSCALE_WS_OD_CAL_DETAIL {
    BPZSCALE_WS_OD_MONTH[] WS_OD_MONTH = new BPZSCALE_WS_OD_MONTH[12];
    String WS_CNTYS_CD = " ";
    String WS_CITY_CD = " ";
    int WS_OD_LST_DATE = 0;
    String WS_OD_LST_TLT = " ";
    public BPZSCALE_WS_OD_CAL_DETAIL() {
        for (int i=0;i<12;i++) WS_OD_MONTH[i] = new BPZSCALE_WS_OD_MONTH();
    }
}
