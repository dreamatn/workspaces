package com.hisun.DD;

public class DDZSQZM_WS_FMT {
    DDZSQZM_WS_CCZM WS_CCZM = new DDZSQZM_WS_CCZM();
    DDZSQZM_WS_AC_LIST[] WS_AC_LIST = new DDZSQZM_WS_AC_LIST[10];
    public DDZSQZM_WS_FMT() {
        for (int i=0;i<10;i++) WS_AC_LIST[i] = new DDZSQZM_WS_AC_LIST();
    }
}
