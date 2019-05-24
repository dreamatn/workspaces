package com.hisun.BP;

public class BPZFSCHF_WS_CARD_DATA_TB {
    int WS_CARD_NUM = 0;
    int WS_CARD_CNT = 0;
    BPZFSCHF_WS_TEM_CARD_TB[] WS_TEM_CARD_TB = new BPZFSCHF_WS_TEM_CARD_TB[20];
    char WS_CCY_FND_FLG = ' ';
    public BPZFSCHF_WS_CARD_DATA_TB() {
        for (int i=0;i<20;i++) WS_TEM_CARD_TB[i] = new BPZFSCHF_WS_TEM_CARD_TB();
    }
}
