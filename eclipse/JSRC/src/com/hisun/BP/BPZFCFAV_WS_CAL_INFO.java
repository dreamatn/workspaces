package com.hisun.BP;

public class BPZFCFAV_WS_CAL_INFO {
    BPZFCFAV_WS_CNT_INFO[] WS_CNT_INFO = new BPZFCFAV_WS_CNT_INFO[10];
    BPZFCFAV_WS_FAV_INFO WS_FAV_INFO = new BPZFCFAV_WS_FAV_INFO();
    short WS_CNT = 0;
    short WS_I = 0;
    short WS_LOC = 0;
    double WS_CONSULT_AMT = 0;
    short WS_CONSULT_CNT = 0;
    int WS_CONSULT_TIME = 0;
    String WS_CONSULT_PRD_CODE = " ";
    int WS_CONSULT_BR = 0;
    String WS_CONSULT_AC_LEVEL = " ";
    String WS_CONSULT_CI_LEVEL = " ";
    String WS_CONSULT_STSW = " ";
    char WS_CONSULT_STS = ' ';
    double WS_SAV_FFAV_FAV_AMT = 0;
    double WS_SAV_FFAV_FAV_PCT = 0;
    public BPZFCFAV_WS_CAL_INFO() {
        for (int i=0;i<10;i++) WS_CNT_INFO[i] = new BPZFCFAV_WS_CNT_INFO();
    }
}
