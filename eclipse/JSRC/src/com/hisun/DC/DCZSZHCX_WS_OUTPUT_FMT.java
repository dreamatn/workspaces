package com.hisun.DC;

public class DCZSZHCX_WS_OUTPUT_FMT {
    DCZSZHCX_WS_PAGE_INF WS_PAGE_INF = new DCZSZHCX_WS_PAGE_INF();
    DCZSZHCX_WS_OUT_DATA[] WS_OUT_DATA = new DCZSZHCX_WS_OUT_DATA[10];
    public DCZSZHCX_WS_OUTPUT_FMT() {
        for (int i=0;i<10;i++) WS_OUT_DATA[i] = new DCZSZHCX_WS_OUT_DATA();
    }
}
