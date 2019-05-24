package com.hisun.BP;

public class BPOT2070_WS_OUT_DATA {
    int TR_DT = 0;
    long JRNNO = 0;
    char CB_TYP = ' ';
    int TM = 0;
    char TX_TYP = ' ';
    char VB_FLG = ' ';
    String PLBOX_NO = " ";
    String CCY = " ";
    double MACH_AMT = 0;
    char FILLER22 = 0X01;
    double ACTU_AMT = 0;
    char FILLER24 = 0X01;
    char STS = ' ';
    BPOT2070_WS_PVAL_INFO[] WS_PVAL_INFO = new BPOT2070_WS_PVAL_INFO[20];
    public BPOT2070_WS_OUT_DATA() {
        for (int i=0;i<20;i++) WS_PVAL_INFO[i] = new BPOT2070_WS_PVAL_INFO();
    }
}
