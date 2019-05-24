package com.hisun.TD;

public class TDZSTZM_WS_FMT {
    TDZSTZM_WS_PAGE_INF WS_PAGE_INF = new TDZSTZM_WS_PAGE_INF();
    TDZSTZM_WS_DATA[] WS_DATA = new TDZSTZM_WS_DATA[8];
    public TDZSTZM_WS_FMT() {
        for (int i=0;i<8;i++) WS_DATA[i] = new TDZSTZM_WS_DATA();
    }
}
