package com.hisun.BP;

public class BPCPQGLM_OUTPUT {
    public String COA_FLG = " ";
    public String SNAME = " ";
    public String LNAME = " ";
    public int EFF_DATE = 0;
    public int EXP_DATE = 0;
    public char OPT_FLG = ' ';
    public String CNTY1 = " ";
    public String CNTY2 = " ";
    public String CNTY3 = " ";
    public char CKFLG = ' ';
    public String REAL_GL = " ";
    public String MEMO_GL = " ";
    public BPCPQGLM_REL_ITMS[] REL_ITMS = new BPCPQGLM_REL_ITMS[60];
    public String UPD_TLR = " ";
    public int UPD_DATE = 0;
    public int UPD_TIME = 0;
    public String SUP_TEL1 = " ";
    public String SUP_TEL2 = " ";
    public BPCPQGLM_OUTPUT() {
        for (int i=0;i<60;i++) REL_ITMS[i] = new BPCPQGLM_REL_ITMS();
    }
}
