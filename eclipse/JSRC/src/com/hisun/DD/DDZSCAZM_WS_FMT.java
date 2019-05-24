package com.hisun.DD;

public class DDZSCAZM_WS_FMT {
    DDZSCAZM_WS_CAZM WS_CAZM = new DDZSCAZM_WS_CAZM();
    DDZSCAZM_WS_CARD_LIST[] WS_CARD_LIST = new DDZSCAZM_WS_CARD_LIST[100];
    public DDZSCAZM_WS_FMT() {
        for (int i=0;i<100;i++) WS_CARD_LIST[i] = new DDZSCAZM_WS_CARD_LIST();
    }
}
