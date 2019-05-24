package com.hisun.CI;

public class CIB5400_AWA_5400 {
    public char EXISTFLG = ' ';
    public short EXISTFLG_NO = 0;
    public String CI_NO = " ";
    public short CI_NO_NO = 0;
    public String ID_TYP = " ";
    public short ID_TYP_NO = 0;
    public String ID_NO = " ";
    public short ID_NO_NO = 0;
    public String ID_CNTY = " ";
    public short ID_CNTY_NO = 0;
    public String ID_STS = " ";
    public short ID_STS_NO = 0;
    public char S_CHKFLG = ' ';
    public short S_CHKFLG_NO = 0;
    public int S_LSDT = 0;
    public short S_LSDT_NO = 0;
    public int EFF_DT = 0;
    public short EFF_DT_NO = 0;
    public int EXP_DT = 0;
    public short EXP_DT_NO = 0;
    public char ID_LVL = ' ';
    public short ID_LVL_NO = 0;
    public String ID_RGN = " ";
    public short ID_RGN_NO = 0;
    public String ID_ORG = " ";
    public short ID_ORG_NO = 0;
    public String S_IDISQ = " ";
    public short S_IDISQ_NO = 0;
    public String DESC = " ";
    public short DESC_NO = 0;
    public char OPEN = ' ';
    public short OPEN_NO = 0;
    public CIB5400_OCCURS33[] OCCURS33 = new CIB5400_OCCURS33[5];
    public int PAGE_ROW = 0;
    public short PAGE_ROW_NO = 0;
    public int PAGE_NUM = 0;
    public short PAGE_NUM_NO = 0;
    public CIB5400_ID_GS[] ID_GS = new CIB5400_ID_GS[10];
    public CIB5400_AWA_5400() {
        for (int i=0;i<5;i++) OCCURS33[i] = new CIB5400_OCCURS33();
        for (int i=0;i<10;i++) ID_GS[i] = new CIB5400_ID_GS();
    }
}
