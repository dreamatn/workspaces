package com.hisun.LN;

public class LNZSTCET_WS_OUT_RECODE {
    LNZSTCET_WS_OUT_HEAD WS_OUT_HEAD = new LNZSTCET_WS_OUT_HEAD();
    LNZSTCET_WS_OUT_DATA[] WS_OUT_DATA = new LNZSTCET_WS_OUT_DATA[10];
    public LNZSTCET_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_DATA[i] = new LNZSTCET_WS_OUT_DATA();
    }
}
