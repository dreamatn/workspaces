package com.hisun.LN;

public class LNOT8100_WS_OUT_RECODE {
    LNOT8100_WS_O_HEAD WS_O_HEAD = new LNOT8100_WS_O_HEAD();
    LNOT8100_WS_O_DATA[] WS_O_DATA = new LNOT8100_WS_O_DATA[10];
    public LNOT8100_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_O_DATA[i] = new LNOT8100_WS_O_DATA();
    }
}
