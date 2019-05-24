package com.hisun.BP;

public class BPCOFAVO_VAL {
    public String DESC = " ";
    public char FILLER5 = 0X02;
    public String FAV_TYPE = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public short FAV_PERIOD = 0;
    public double MAX_FAV_AMT = 0;
    public char FILLER11 = 0X01;
    public double MAX_FAV_PCT = 0;
    public char FILLER13 = 0X01;
    public char FAV_COLL = ' ';
    public String FAV_CPNT = " ";
    public char CAL_SOURCE = ' ';
    public char CAL_CYC = ' ';
    public short CYC_NUM = 0;
    public char COLL_TYPE = ' ';
    public char FAV_SPLT_FLG = ' ';
    public char ADJ_TYP = ' ';
    public String DIF_VAL = " ";
    public BPCOFAVO_FAV_11[] FAV_11 = new BPCOFAVO_FAV_11[10];
    public BPCOFAVO_FAV_22[] FAV_22 = new BPCOFAVO_FAV_22[10];
    public BPCOFAVO_FAV_33[] FAV_33 = new BPCOFAVO_FAV_33[10];
    public BPCOFAVO_FAV_44[] FAV_44 = new BPCOFAVO_FAV_44[10];
    public BPCOFAVO_FAV_55[] FAV_55 = new BPCOFAVO_FAV_55[10];
    public BPCOFAVO_FAV_66[] FAV_66 = new BPCOFAVO_FAV_66[10];
    public BPCOFAVO_FAV_77[] FAV_77 = new BPCOFAVO_FAV_77[10];
    public BPCOFAVO_FAV_88[] FAV_88 = new BPCOFAVO_FAV_88[10];
    public BPCOFAVO_FAV_99[] FAV_99 = new BPCOFAVO_FAV_99[10];
    public BPCOFAVO_FAV_101[] FAV_101 = new BPCOFAVO_FAV_101[10];
    public int LAST_DATE = 0;
    public String LAST_TELL = " ";
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCOFAVO_VAL() {
        for (int i=0;i<10;i++) FAV_11[i] = new BPCOFAVO_FAV_11();
        for (int i=0;i<10;i++) FAV_22[i] = new BPCOFAVO_FAV_22();
        for (int i=0;i<10;i++) FAV_33[i] = new BPCOFAVO_FAV_33();
        for (int i=0;i<10;i++) FAV_44[i] = new BPCOFAVO_FAV_44();
        for (int i=0;i<10;i++) FAV_55[i] = new BPCOFAVO_FAV_55();
        for (int i=0;i<10;i++) FAV_66[i] = new BPCOFAVO_FAV_66();
        for (int i=0;i<10;i++) FAV_77[i] = new BPCOFAVO_FAV_77();
        for (int i=0;i<10;i++) FAV_88[i] = new BPCOFAVO_FAV_88();
        for (int i=0;i<10;i++) FAV_99[i] = new BPCOFAVO_FAV_99();
        for (int i=0;i<10;i++) FAV_101[i] = new BPCOFAVO_FAV_101();
    }
}
