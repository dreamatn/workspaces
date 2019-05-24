package com.hisun.LN;

public class LNZSRATH_WS_OUT_RECODE {
    LNZSRATH_WS_OUT_HEAD WS_OUT_HEAD = new LNZSRATH_WS_OUT_HEAD();
    LNZSRATH_WS_OUT_DATA[] WS_OUT_DATA = new LNZSRATH_WS_OUT_DATA[25];
    public LNZSRATH_WS_OUT_RECODE() {
        for (int i=0;i<25;i++) WS_OUT_DATA[i] = new LNZSRATH_WS_OUT_DATA();
    }
}
