package com.hisun.LN;

public class LNZSBEXT_WS_OUT_RECODE {
    LNZSBEXT_WS_O_HEAD WS_O_HEAD = new LNZSBEXT_WS_O_HEAD();
    LNZSBEXT_WS_O_DATA[] WS_O_DATA = new LNZSBEXT_WS_O_DATA[25];
    public LNZSBEXT_WS_OUT_RECODE() {
        for (int i=0;i<25;i++) WS_O_DATA[i] = new LNZSBEXT_WS_O_DATA();
    }
}
