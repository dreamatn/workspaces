package com.hisun.DD;

public class DDZSCINT_WS_LIST {
    DDZSCINT_WS_PAGE_INF WS_PAGE_INF = new DDZSCINT_WS_PAGE_INF();
    DDZSCINT_WS_OUTPUT_LST[] WS_OUTPUT_LST = new DDZSCINT_WS_OUTPUT_LST[25];
    public DDZSCINT_WS_LIST() {
        for (int i=0;i<25;i++) WS_OUTPUT_LST[i] = new DDZSCINT_WS_OUTPUT_LST();
    }
}
