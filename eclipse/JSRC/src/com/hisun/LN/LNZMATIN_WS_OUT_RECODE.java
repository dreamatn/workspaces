package com.hisun.LN;

public class LNZMATIN_WS_OUT_RECODE {
    LNZMATIN_WS_OUT_HEAD WS_OUT_HEAD = new LNZMATIN_WS_OUT_HEAD();
    LNZMATIN_WS_OUT_INFO[] WS_OUT_INFO = new LNZMATIN_WS_OUT_INFO[25];
    public LNZMATIN_WS_OUT_RECODE() {
        for (int i=0;i<25;i++) WS_OUT_INFO[i] = new LNZMATIN_WS_OUT_INFO();
    }
}
