package com.hisun.TD;

public class TDZSLMCT_WS_OUTPUT {
    TDZSLMCT_WS_PAGE_INF WS_PAGE_INF = new TDZSLMCT_WS_PAGE_INF();
    TDZSLMCT_WS_DATA[] WS_DATA = new TDZSLMCT_WS_DATA[20];
    public TDZSLMCT_WS_OUTPUT() {
        for (int i=0;i<20;i++) WS_DATA[i] = new TDZSLMCT_WS_DATA();
    }
}
