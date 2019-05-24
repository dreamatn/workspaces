package com.hisun.BP;

public class BPZFSCHF_WS_CI_DATA_TB {
    int WS_AC_NUM = 0;
    int WS_AC_CNT = 0;
    BPZFSCHF_WS_TEM_AC_TB[] WS_TEM_AC_TB = new BPZFSCHF_WS_TEM_AC_TB[20];
    char WS_AC_FND_FLG = ' ';
    public BPZFSCHF_WS_CI_DATA_TB() {
        for (int i=0;i<20;i++) WS_TEM_AC_TB[i] = new BPZFSCHF_WS_TEM_AC_TB();
    }
}
