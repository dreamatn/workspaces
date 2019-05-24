package com.hisun.LN;

public class LNOT3200_WS_OUTPUT {
    String WS_CONTRACT_NO = " ";
    long WS_TRAN_SEQ = 0;
    char WS_PAY_TYPE = ' ';
    LNOT3200_WS_LIST[] WS_LIST = new LNOT3200_WS_LIST[20];
    public LNOT3200_WS_OUTPUT() {
        for (int i=0;i<20;i++) WS_LIST[i] = new LNOT3200_WS_LIST();
    }
}
