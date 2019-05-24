package com.hisun.BP;

public class BPZPCHDY_WS_OD_CAL_DETAIL {
    BPZPCHDY_WS_OD_MONTH[] WS_OD_MONTH = new BPZPCHDY_WS_OD_MONTH[12];
    String WS_OD_CNTYS_CD = " ";
    String WS_OD_CITY_CD = " ";
    int WS_OD_LST_DATE = 0;
    String WS_OD_LST_TLT = " ";
    public BPZPCHDY_WS_OD_CAL_DETAIL() {
        for (int i=0;i<12;i++) WS_OD_MONTH[i] = new BPZPCHDY_WS_OD_MONTH();
    }
}
