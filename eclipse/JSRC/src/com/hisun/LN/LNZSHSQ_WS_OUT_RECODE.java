package com.hisun.LN;

public class LNZSHSQ_WS_OUT_RECODE {
    LNZSHSQ_WS_OUT_HEAD WS_OUT_HEAD = new LNZSHSQ_WS_OUT_HEAD();
    LNZSHSQ_WS_OUT_INFO[] WS_OUT_INFO = new LNZSHSQ_WS_OUT_INFO[10];
    public LNZSHSQ_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_INFO[i] = new LNZSHSQ_WS_OUT_INFO();
    }
}
