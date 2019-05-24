package com.hisun.BP;

public class BPOT2203_WS_TLVB_REC {
    char PLBOX_TYPE = ' ';
    char VB_FLG = ' ';
    String PLBOX_NO = " ";
    BPOT2203_WS_CCY_INFO[] WS_CCY_INFO = new BPOT2203_WS_CCY_INFO[20];
    public BPOT2203_WS_TLVB_REC() {
        for (int i=0;i<20;i++) WS_CCY_INFO[i] = new BPOT2203_WS_CCY_INFO();
    }
}
