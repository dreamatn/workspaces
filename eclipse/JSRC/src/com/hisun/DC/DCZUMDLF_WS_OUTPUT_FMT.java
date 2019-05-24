package com.hisun.DC;

public class DCZUMDLF_WS_OUTPUT_FMT {
    DCZUMDLF_WS_PAGE_INF WS_PAGE_INF = new DCZUMDLF_WS_PAGE_INF();
    DCZUMDLF_WS_OUT_DATA[] WS_OUT_DATA = new DCZUMDLF_WS_OUT_DATA[10];
    public DCZUMDLF_WS_OUTPUT_FMT() {
        for (int i=0;i<10;i++) WS_OUT_DATA[i] = new DCZUMDLF_WS_OUT_DATA();
    }
}
