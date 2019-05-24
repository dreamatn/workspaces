package com.hisun.BP;

public class BPZMEDA_WS_BPRSPCA {
    BPZMEDA_WS_SPCA_KEY WS_SPCA_KEY = new BPZMEDA_WS_SPCA_KEY();
    char WS_SPCA_CMP_FLG = ' ';
    short WS_SPCA_CNT = 0;
    BPZMEDA_WS_SPCA_AUTH_SET[] WS_SPCA_AUTH_SET = new BPZMEDA_WS_SPCA_AUTH_SET[10];
    int WS_SPCA_CRE_DT = 0;
    int WS_SPCA_CRE_BR = 0;
    String WS_SPCA_CRE_TLR = " ";
    int WS_SPCA_UPD_DT = 0;
    int WS_SPCA_UPD_TM = 0;
    int WS_SPCA_UPD_BR = 0;
    String WS_SPCA_UPD_TLR = " ";
    String WS_SPCA_TS = " ";
    public BPZMEDA_WS_BPRSPCA() {
        for (int i=0;i<10;i++) WS_SPCA_AUTH_SET[i] = new BPZMEDA_WS_SPCA_AUTH_SET();
    }
}
