package com.hisun.AI;

public class AIOMP05_WS_OUT_PUT1 {
    String WS_O_COA_FLG = " ";
    String WS_O_COA_NO = " ";
    String WS_O_ENAME = " ";
    String WS_O_CNAME = " ";
    char AIOMP05_FILLER127 = 0X02;
    char WS_O_ITM_LVL = ' ';
    String WS_O_UP_ITM = " ";
    char WS_O_RED_FLG = ' ';
    String WS_O_COA_TYP = " ";
    char WS_O_COA_CAT = ' ';
    char WS_O_POSTTYP = ' ';
    char WS_O_AUTO_GEN = ' ';
    char WS_O_LOW_IND = ' ';
    char WS_O_FXR_IND = ' ';
    char WS_O_PLT_IND = ' ';
    int WS_O_MST_COA = 0;
    char WS_O_MIB_FLG = ' ';
    String WS_O_LKUP_CD = " ";
    short WS_O_SEN_COA = 0;
    char WS_O_BAL_IND = ' ';
    char WS_O_DR_CR = ' ';
    char WS_O_AUTO_MAT = ' ';
    char WS_O_AUTO_MAT_N = ' ';
    char WS_O_AUTH_LVL = ' ';
    char WS_O_COA_STS = ' ';
    int WS_O_EFF_DAT = 0;
    int WS_O_EXP_DAT = 0;
    char WS_O_ITM_SEGMENT = ' ';
    int WS_O_HOL_DAT = 0;
    char WS_O_SL_FLG = ' ';
    String WS_O_TS = " ";
    AIOMP05_WS_O_ANS_ARY[] WS_O_ANS_ARY = new AIOMP05_WS_O_ANS_ARY[10];
    public AIOMP05_WS_OUT_PUT1() {
        for (int i=0;i<10;i++) WS_O_ANS_ARY[i] = new AIOMP05_WS_O_ANS_ARY();
    }
}
