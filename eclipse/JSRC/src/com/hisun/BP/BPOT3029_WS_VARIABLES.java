package com.hisun.BP;

public class BPOT3029_WS_VARIABLES {
    String ERR_MSG = " ";
    short FLD_NO = 0;
    int I = 0;
    int CNT = 0;
    int J = 0;
    long CONF_NO = 0;
    char TBL_FLAG = ' ';
    BPOT3029_WS_APP_INFO WS_APP_INFO = new BPOT3029_WS_APP_INFO();
    int APP_NO = 0;
    String BV_CODE = " ";
    String BV_NAME = " ";
    BPOT3029_WS_BV_INFO[] WS_BV_INFO = new BPOT3029_WS_BV_INFO[4];
    public BPOT3029_WS_VARIABLES() {
        for (int i=0;i<4;i++) WS_BV_INFO[i] = new BPOT3029_WS_BV_INFO();
    }
}
