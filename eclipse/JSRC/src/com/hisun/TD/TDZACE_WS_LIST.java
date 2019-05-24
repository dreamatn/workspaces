package com.hisun.TD;

public class TDZACE_WS_LIST {
    TDZACE_WS_PAGE_INF WS_PAGE_INF = new TDZACE_WS_PAGE_INF();
    TDZACE_WS_DATA[] WS_DATA = new TDZACE_WS_DATA[6];
    public TDZACE_WS_LIST() {
        for (int i=0;i<6;i++) WS_DATA[i] = new TDZACE_WS_DATA();
    }
}
