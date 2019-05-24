package com.hisun.BP;

public class BPCOPBL {
    public String CNTR_TYP = " ";
    public String PROD_TYP = " ";
    public int BR = 0;
    public String MOD_NO = " ";
    public String MOD_NAME = " ";
    public char FILLER6 = 0X02;
    public String BOOK_FLG = " ";
    public short CNT = 0;
    public String ITM_PNT = " ";
    public String ITM_NO = " ";
    public int ITM_SEQ = 0;
    public BPCOPBL_AMT_PNT[] AMT_PNT = new BPCOPBL_AMT_PNT[20];
    public String REMARK = " ";
    public char FILLER16 = 0X02;
    public int EFF_DT = 0;
    public int EXP_DT = 0;
    public char RUN_FLG = ' ';
    public BPCOPBL() {
        for (int i=0;i<20;i++) AMT_PNT[i] = new BPCOPBL_AMT_PNT();
    }
}
