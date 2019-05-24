package com.hisun.DD;

public class DDZSCCZM_WS_FMT {
    DDZSCCZM_WS_CCZM WS_CCZM = new DDZSCCZM_WS_CCZM();
    DDZSCCZM_WS_AC_LIST[] WS_AC_LIST = new DDZSCCZM_WS_AC_LIST[10];
    public DDZSCCZM_WS_FMT() {
        for (int i=0;i<10;i++) WS_AC_LIST[i] = new DDZSCCZM_WS_AC_LIST();
    }
}
