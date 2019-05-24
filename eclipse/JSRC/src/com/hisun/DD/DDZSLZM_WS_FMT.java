package com.hisun.DD;

public class DDZSLZM_WS_FMT {
    DDZSLZM_WS_PAGE_INF WS_PAGE_INF = new DDZSLZM_WS_PAGE_INF();
    DDZSLZM_WS_DATA[] WS_DATA = new DDZSLZM_WS_DATA[10];
    public DDZSLZM_WS_FMT() {
        for (int i=0;i<10;i++) WS_DATA[i] = new DDZSLZM_WS_DATA();
    }
}
