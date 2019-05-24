package com.hisun.TD;

public class TDOT5020_WS_LIST {
    String RUL_CD = " ";
    String CCY = " ";
    String CDESC = " ";
    char FILLER30 = 0X02;
    String DESC = " ";
    int SDT = 0;
    int DDT = 0;
    TDOT5020_WS_OC_RACD[] WS_OC_RACD = new TDOT5020_WS_OC_RACD[31];
    String SMK = " ";
    char MY_FLG = ' ';
    String OUTPUT_FMT1 = "TD502";
    public TDOT5020_WS_LIST() {
        for (int i=0;i<31;i++) WS_OC_RACD[i] = new TDOT5020_WS_OC_RACD();
    }
}
