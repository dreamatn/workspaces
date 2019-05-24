package com.hisun.TD;

public class TDZCRACD_WS_AC_BAS_INFO {
    double WS_BAL = 0;
    String WS_AC_TERM = " ";
    String WS_SVR_LVL = " ";
    TDZCRACD_WS_GRPS_GRP[] WS_GRPS_GRP = new TDZCRACD_WS_GRPS_GRP[30];
    int WS_BR = 0;
    String WS_RGN_NO = " ";
    String WS_ASS_LVL = " ";
    String WS_CHNL_NO = " ";
    short WS_AGE = 0;
    int WS_BIRTH = 0;
    TDZCRACD_REDEFINES52 REDEFINES52 = new TDZCRACD_REDEFINES52();
    int WS_TODAY = 0;
    TDZCRACD_REDEFINES56 REDEFINES56 = new TDZCRACD_REDEFINES56();
    char WS_GENDER = ' ';
    public TDZCRACD_WS_AC_BAS_INFO() {
        for (int i=0;i<30;i++) WS_GRPS_GRP[i] = new TDZCRACD_WS_GRPS_GRP();
    }
}
