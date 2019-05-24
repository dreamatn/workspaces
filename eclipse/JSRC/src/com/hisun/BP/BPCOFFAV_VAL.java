package com.hisun.BP;

public class BPCOFFAV_VAL {
    public String DESC = " ";
    public char FILLER28 = 0X02;
    public String FAV_TYPE = " ";
    public short FAV_PERIOD = 0;
    public double MAX_FAV_AMT = 0;
    public char FILLER32 = 0X01;
    public double MAX_FAV_PCT = 0;
    public char FILLER34 = 0X01;
    public char FAV_COLL = ' ';
    public String FAV_CPNT = " ";
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char COLL_TYPE = ' ';
    public char FAV_SPLT_FLG = ' ';
    public char ADJ_TYP = ' ';
    public String DIF_VAL = " ";
    public BPCOFFAV_FAV_DATA[] FAV_DATA = new BPCOFFAV_FAV_DATA[10];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public String TS = " ";
    public BPCOFFAV_VAL() {
        for (int i=0;i<10;i++) FAV_DATA[i] = new BPCOFFAV_FAV_DATA();
    }
}
