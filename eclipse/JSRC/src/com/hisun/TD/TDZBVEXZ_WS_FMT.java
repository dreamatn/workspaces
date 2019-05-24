package com.hisun.TD;

public class TDZBVEXZ_WS_FMT {
    TDZBVEXZ_WS_PAGE_INF WS_PAGE_INF = new TDZBVEXZ_WS_PAGE_INF();
    TDZBVEXZ_WS_DATA[] WS_DATA = new TDZBVEXZ_WS_DATA[10];
    public TDZBVEXZ_WS_FMT() {
        for (int i=0;i<10;i++) WS_DATA[i] = new TDZBVEXZ_WS_DATA();
    }
}
