package com.hisun.TD;

public class TDZBVEXC_WS_FMT {
    TDZBVEXC_WS_PAGE_INF WS_PAGE_INF = new TDZBVEXC_WS_PAGE_INF();
    TDZBVEXC_WS_DATA[] WS_DATA = new TDZBVEXC_WS_DATA[10];
    public TDZBVEXC_WS_FMT() {
        for (int i=0;i<10;i++) WS_DATA[i] = new TDZBVEXC_WS_DATA();
    }
}
