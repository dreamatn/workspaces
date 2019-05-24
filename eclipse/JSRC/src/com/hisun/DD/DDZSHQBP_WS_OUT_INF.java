package com.hisun.DD;

public class DDZSHQBP_WS_OUT_INF {
    char WS_HQBP_FUNC = ' ';
    String WS_HQBP_PRD_CD = " ";
    String WS_HQBP_CHN_NAME = " ";
    char DDZSHQBP_FILLER8 = 0X02;
    int WS_HQBP_EFF_DATE = 0;
    int WS_HQBP_EXP_DATE = 0;
    short WS_HQBP_PERIOD = 0;
    char WS_HQBP_AUTO_FLG = ' ';
    String WS_HQBP_CCY = " ";
    double WS_HQBP_MIN_AMT = 0;
    char WS_HQBP_CHK_PERD = ' ';
    short WS_HQBP_CHK_DAY = 0;
    char WS_HQBP_PST_PERD = ' ';
    short WS_HQBP_PST_DATE = 0;
    char WS_HQBP_CAL_COND = ' ';
    char WS_HQBP_TIER_TYP = ' ';
    DDZSHQBP_WS_HQBP_TIER_IR[] WS_HQBP_TIER_IR = new DDZSHQBP_WS_HQBP_TIER_IR[5];
    public DDZSHQBP_WS_OUT_INF() {
        for (int i=0;i<5;i++) WS_HQBP_TIER_IR[i] = new DDZSHQBP_WS_HQBP_TIER_IR();
    }
}
