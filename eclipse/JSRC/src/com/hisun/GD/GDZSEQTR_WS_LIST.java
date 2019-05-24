package com.hisun.GD;

public class GDZSEQTR_WS_LIST {
    GDZSEQTR_WS_PAGE_INF WS_PAGE_INF = new GDZSEQTR_WS_PAGE_INF();
    GDZSEQTR_WS_OUTPUT_LST[] WS_OUTPUT_LST = new GDZSEQTR_WS_OUTPUT_LST[25];
    public GDZSEQTR_WS_LIST() {
        for (int i=0;i<25;i++) WS_OUTPUT_LST[i] = new GDZSEQTR_WS_OUTPUT_LST();
    }
}
