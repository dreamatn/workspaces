package com.hisun.BP;

public class BPOT5384_WS_OUTPUT_DATA {
    String WS_FAV_CODE = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    String WS_FAV_DESC = " ";
    String WS_FAV_CDESC = " ";
    char BPOT5384_FILLER10 = 0X02;
    String WS_CCY = " ";
    String WS_FEE_TYPE = " ";
    char WS_CAL_MTH = ' ';
    BPOT5384_WS_GROUP[] WS_GROUP = new BPOT5384_WS_GROUP[10];
    int WS_UP_DT = 0;
    int WS_UPD_TM = 0;
    int WS_UPD_BR = 0;
    String WS_UPD_TLR = " ";
    public BPOT5384_WS_OUTPUT_DATA() {
        for (int i=0;i<10;i++) WS_GROUP[i] = new BPOT5384_WS_GROUP();
    }
}
