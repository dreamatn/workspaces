package com.hisun.BP;

public class BPCOGLMM {
    public int MSTNO = 0;
    public String COA_FLG = " ";
    public String SNAME = " ";
    public String LNAME = " ";
    public char FILLER5 = 0X02;
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public String CNTY1 = " ";
    public String CNTY2 = " ";
    public String CNTY3 = " ";
    public char CKFLG = ' ';
    public BPCOGLMM_REL_ITMS[] REL_ITMS = new BPCOGLMM_REL_ITMS[60];
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public int P_EFF_DATE = 0;
    public int P_EXP_DATE = 0;
    public BPCOGLMM() {
        for (int i=0;i<60;i++) REL_ITMS[i] = new BPCOGLMM_REL_ITMS();
    }
}
