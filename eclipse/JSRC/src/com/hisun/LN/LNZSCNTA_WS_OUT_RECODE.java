package com.hisun.LN;

public class LNZSCNTA_WS_OUT_RECODE {
    LNZSCNTA_WS_OUT_HEAD WS_OUT_HEAD = new LNZSCNTA_WS_OUT_HEAD();
    LNZSCNTA_WS_OUT_INFO[] WS_OUT_INFO = new LNZSCNTA_WS_OUT_INFO[10];
    public LNZSCNTA_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_INFO[i] = new LNZSCNTA_WS_OUT_INFO();
    }
}
