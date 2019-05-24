package com.hisun.LN;

public class LNZLETIF_WS_OUT_RECODE {
    LNZLETIF_WS_OUT_HEAD WS_OUT_HEAD = new LNZLETIF_WS_OUT_HEAD();
    LNZLETIF_WS_OUT_INFO[] WS_OUT_INFO = new LNZLETIF_WS_OUT_INFO[10];
    public LNZLETIF_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_INFO[i] = new LNZLETIF_WS_OUT_INFO();
    }
}
