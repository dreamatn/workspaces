package com.hisun.BP;

public class BPZUIHIS_WS_LOCAL_ACTY {
    String WS_CHECK_AC = " ";
    String WS_CHECK_CCY = " ";
    char WS_ACTY_FND_FLG = ' ';
    int WS_ACTY_CNT = 0;
    int WS_ACTY_NUM = 0;
    BPZUIHIS_WS_ACTY_TABLE[] WS_ACTY_TABLE = new BPZUIHIS_WS_ACTY_TABLE[20];
    public BPZUIHIS_WS_LOCAL_ACTY() {
        for (int i=0;i<20;i++) WS_ACTY_TABLE[i] = new BPZUIHIS_WS_ACTY_TABLE();
    }
}
