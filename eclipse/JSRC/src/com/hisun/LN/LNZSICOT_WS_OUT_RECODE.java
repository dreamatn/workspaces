package com.hisun.LN;

public class LNZSICOT_WS_OUT_RECODE {
    LNZSICOT_WS_OUT_HEAD WS_OUT_HEAD = new LNZSICOT_WS_OUT_HEAD();
    LNZSICOT_WS_OUT_INFO[] WS_OUT_INFO = new LNZSICOT_WS_OUT_INFO[25];
    public LNZSICOT_WS_OUT_RECODE() {
        for (int i=0;i<25;i++) WS_OUT_INFO[i] = new LNZSICOT_WS_OUT_INFO();
    }
}
