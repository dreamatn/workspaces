package com.hisun.LN;

public class LNZSSCHE_WS_OUT_RECODE {
    LNZSSCHE_WS_OUT_HEAD WS_OUT_HEAD = new LNZSSCHE_WS_OUT_HEAD();
    LNZSSCHE_WS_OUT_INFO[] WS_OUT_INFO = new LNZSSCHE_WS_OUT_INFO[10];
    public LNZSSCHE_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_INFO[i] = new LNZSSCHE_WS_OUT_INFO();
    }
}
