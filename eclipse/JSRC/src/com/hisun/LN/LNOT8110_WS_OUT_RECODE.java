package com.hisun.LN;

public class LNOT8110_WS_OUT_RECODE {
    LNOT8110_WS_OUT_HEAD WS_OUT_HEAD = new LNOT8110_WS_OUT_HEAD();
    LNOT8110_WS_OUT_DATA[] WS_OUT_DATA = new LNOT8110_WS_OUT_DATA[10];
    public LNOT8110_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_DATA[i] = new LNOT8110_WS_OUT_DATA();
    }
}
