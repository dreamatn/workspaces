package com.hisun.AI;

public class AIOMP05_WS_TSQ_FLD {
    String WS_COA_FLG = " ";
    String WS_COA_NO = " ";
    char WS_ITM_LVL = ' ';
    String WS_UP_ITM = " ";
    char WS_STS = ' ';
    char WS_SEGMENT = ' ';
    String WS_ENAME = " ";
    String WS_CNAME = " ";
    char AIOMP05_FILLER88 = 0X02;
    String WS_COA_TYP = " ";
    char WS_COA_CAT = ' ';
    char WS_POSTTYP = ' ';
    char WS_AUTO_GEN = ' ';
    char WS_LOW_IND = ' ';
    char WS_FXR_IND = ' ';
    int WS_MST_COA = 0;
    String WS_LOOK_CD = " ";
    short WS_SEN_COA = 0;
    char WS_BAL_IND = ' ';
    char WS_DR_CR = ' ';
    char WS_AUTO_MAT = ' ';
    char WS_AUTH_LVL = ' ';
    int WS_EFF_DAT = 0;
    int WS_EXP_DAT = 0;
    String WS_LST_UTLR = " ";
    String WS_TS = " ";
    AIOMP05_WS_ANS_ARY[] WS_ANS_ARY = new AIOMP05_WS_ANS_ARY[10];
    int WS_HOL_DATE = 0;
    char WS_RED_FLG = ' ';
    char WS_MIB_FLG = ' ';
    public AIOMP05_WS_TSQ_FLD() {
        for (int i=0;i<10;i++) WS_ANS_ARY[i] = new AIOMP05_WS_ANS_ARY();
    }
}
