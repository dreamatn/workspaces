package com.hisun.LN;

public class LNZIETIF_WS_OUT_RECODE {
    LNZIETIF_WS_OUT_HEAD WS_OUT_HEAD = new LNZIETIF_WS_OUT_HEAD();
    LNZIETIF_WS_OUT_INFO[] WS_OUT_INFO = new LNZIETIF_WS_OUT_INFO[10];
    public LNZIETIF_WS_OUT_RECODE() {
        for (int i=0;i<10;i++) WS_OUT_INFO[i] = new LNZIETIF_WS_OUT_INFO();
    }
}
