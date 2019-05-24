package com.hisun.LN;

public class LNZSMARQ_WS_OUT_RECODE {
    LNZSMARQ_WS_OUT_HEAD WS_OUT_HEAD = new LNZSMARQ_WS_OUT_HEAD();
    LNZSMARQ_WS_OUT_DATA[] WS_OUT_DATA = new LNZSMARQ_WS_OUT_DATA[10];
    public LNZSMARQ_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_DATA[i] = new LNZSMARQ_WS_OUT_DATA();
    }
}
