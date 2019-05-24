package com.hisun.BP;

public class BPZUITD_WS_VAL {
    BPZUITD_WS_REL_ITMS[] WS_REL_ITMS = new BPZUITD_WS_REL_ITMS[32];
    String WS_UPD_TLR = " ";
    int WS_UPD_DATE = 0;
    int WS_UPD_TIME = 0;
    String WS_SUP_TEL1 = " ";
    String WS_SUP_TEL2 = " ";
    public BPZUITD_WS_VAL() {
        for (int i=0;i<32;i++) WS_REL_ITMS[i] = new BPZUITD_WS_REL_ITMS();
    }
}
